

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.neuedu.guomy.business.LoginManager;

public class MainFrame extends JFrame {
	JPanel pnlMain = null;
	JPanel pnlBottom = null;
	JTextArea txtContent = null;
	JList lstUsers = null;
	JTextField txtSend = null;
	JButton btnSend = null;
	DataOutputStream dos = null;
	Socket socket = null;
	ServerSocket serverSocket = null;
	List<SocketThread> socketThreads = new ArrayList<SocketThread>();
	List users  = new ArrayList();

	private boolean stopped = false;

	public MainFrame() throws HeadlessException {
		init();
	}

	private void init() {
		this.setSize(new Dimension(400, 300));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("�����");

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				//Ŀ�ģ�ֹͣ����˵����߳�
				stopped = true;
				
				//��֪�ͻ��ˣ�������ѹر�
				for (SocketThread socketThread : socketThreads) {
					socketThread.writeData("ServerClosed");
				}
				
				//Ŀ�ģ���ֹ��serverSocket.accept();�̹߳���������������һ�����ӣ��Ӷ�����ѭ���������̣߳�
				Socket socket1 = null;
				try {
					if (socket1 == null)
						socket1 = new Socket("127.0.0.1", 9028);
					DataOutputStream dos1 = new DataOutputStream(socket1.getOutputStream());
					dos1.writeUTF("ServerStop");
					dos1.flush();
//					System.out.println("ServerStop�ѷ�����");
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						if(socket1 != null) {
							socket1.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				
			
			
			}
		});

		pnlMain = new JPanel();
		pnlMain.setLayout(new BorderLayout());
		this.setContentPane(pnlMain);

		txtContent = new JTextArea();
		txtContent.setBackground(new Color(200, 200, 200));
		pnlMain.add(txtContent, BorderLayout.CENTER);

		//�û��б�
		users.add("ȫ���û�");
		lstUsers = new JList(users.toArray());
		pnlMain.add(lstUsers, BorderLayout.EAST);

		pnlBottom = new JPanel();
		txtSend = new JTextField();
		txtSend.setColumns(20);

		btnSend = new JButton("����");
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DataOutputStream dos = null;

				for (SocketThread socketThread : socketThreads) {
					socketThread.writeData(String.format("Server:%s:%s", txtSend.getText(),new Timestamp(System.currentTimeMillis()).toString()));
				}
				txtContent.setText(txtContent.getText() + "\nServer��" + txtSend.getText() );
				txtSend.setText("");
			}
		});

		pnlBottom.add(txtSend);
		pnlBottom.add(btnSend);
		pnlMain.add(pnlBottom, BorderLayout.SOUTH);
		this.setVisible(true);

		try {
			serverSocket = new ServerSocket(9028);

			while (!stopped) {
				socket = serverSocket.accept();	// �̹߳�������
				//����һ���̣߳��βΣ���ǰ���ӣ��ı������ã������û��б�
				SocketThread socketThread = new SocketThread(socket, txtContent, socketThreads,this);
				socketThreads.add(socketThread);
				Thread thread = new Thread(socketThread);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null){
					socket.close();
				}
				if (serverSocket != null){
					serverSocket.close();
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


	public void showUsers(){
		users = new ArrayList();
		for (SocketThread socketThread :  socketThreads) {
			users.add(socketThread.getName());
		}
		lstUsers.setListData(users.toArray());
		pnlMain.add(lstUsers, BorderLayout.EAST);
		lstUsers.repaint();
		pnlMain.repaint();
	}

	/*
	 * ������
	 */
	public void writeData(String str) {
		
		if (socket != null) {	 
			try {
				dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally { 
 
			}
		}
	}

}
