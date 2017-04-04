
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MainFrame extends JFrame implements Runnable {
	JPanel pnlMain = null;
	JPanel pnlBottom = null;
	JTextArea txtContent = null;
	JList lstUsers = null;
	JTextField txtSend = null;
	JButton btnSend = null;
	Socket socket = null;
	DataOutputStream dos = null;
	String loginName = null;
	List users = new ArrayList();
	
	public MainFrame() throws HeadlessException {
		init();
	}
	public MainFrame(Socket socket, String loginName) throws HeadlessException {
		this.socket = socket;
		this.loginName = loginName;
		init();
	}

	private void init() {
		this.setSize(new Dimension(400, 300));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("�û�����" + loginName);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				writeData("Client_Request_Exit");
			}

		});


		pnlMain = new JPanel();
		pnlMain.setLayout(new BorderLayout());
		this.setContentPane(pnlMain);

		txtContent = new JTextArea();
		txtContent.setText("Let's begin Chatting��");
		txtContent.setBackground(new Color(100, 200, 100));
		pnlMain.add(txtContent, BorderLayout.CENTER);

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
				if (socket != null) {

					Object obj = lstUsers.getSelectedValue();
					String str  = null;
					if(obj == null || obj.toString().equals("ȫ���û�")){
						//����Ⱥ����Ϣ�� ��ʽΪ ChatAll:���� 
						str =  String.format("ChatAll:%s", txtSend.getText());
					} else{
						//����һ��һ������Ϣ����ʽΪ  Chat:���շ������� 
						str =  String.format("Chat:%s:%s", obj.toString(), txtSend.getText());
					}
					writeData(str);

					txtSend.setText("");
				}
			}
		});

		pnlBottom.add(txtSend);
		pnlBottom.add(btnSend);
		pnlMain.add(pnlBottom, BorderLayout.SOUTH);
		this.setVisible(true);

		try {
			if (socket == null)
				socket = new Socket("127.0.0.1", 9028);
			Thread thread = new Thread(this);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { }
	}

	@Override
	public void run() {
		DataInputStream dis = null;
		if (socket != null) {
			try {
				dis = new DataInputStream(socket.getInputStream());
				while (true) {
					String str = dis.readUTF();	// �̹߳���
					//System.out.println(str);
					String [] strs = str.split(":"); 

					//�����������Ϣ
					if (strs[0].compareToIgnoreCase("Server") == 0) {
						txtContent.setText(txtContent.getText() + "\n���������"  +":" + strs[1] + "������������" + strs[2]+":"+strs[3] );
					}
					//�������죨���ĺ�Ⱥ�ģ�����://Chat:���ͷ������ݣ�ʱ��   ChatAll:���ͷ������ݣ�ʱ��
					else if (strs[0].compareToIgnoreCase("Chat") == 0 || strs[0].compareToIgnoreCase("ChatAll") == 0) {
						txtContent.setText(txtContent.getText() + "\n�û�" + strs[1] +":" + strs[2] + "������������" + strs[3]+":"+strs[4]);
					}
					//��ȡ�û��б����
					else if (strs[0].compareToIgnoreCase("UserList") == 0) {
						for (int i = 1; i < strs.length; i++) {
							users.add(strs[i]);
						}
						lstUsers.setListData(users.toArray());
						pnlMain.add(lstUsers, BorderLayout.EAST);
						lstUsers.repaint();
						pnlMain.repaint();
					}
					//��ӵ����û�---�������ߵĿͻ�����ӵ��û��б���
					else if (strs[0].compareToIgnoreCase("AddUser") == 0) {
						users.add(strs[1]);
						lstUsers.setListData(users.toArray());
						pnlMain.add(lstUsers, BorderLayout.EAST);
						lstUsers.repaint();
						pnlMain.repaint();
					}
					//�Ƴ������û�---�������ߵĿͻ��˴��û��б��Ƴ� 
					else if (strs[0].compareToIgnoreCase("RemoveUser") == 0) {
						for(Object userName: users){
							if(userName.toString().compareTo(strs[1]) == 0){
								users.remove(userName);
								break;
							}
						}
						lstUsers.setListData(users.toArray());
						pnlMain.add(lstUsers,BorderLayout.EAST);
						lstUsers.repaint();
						pnlMain.repaint();
					}
					else if (str.compareTo("Server_Agree_Exit") == 0) {
						break;   // �����߳�
					}
					//----
					else if (str.compareTo("ServerClosed") == 0)
					{
						writeData("ClientClosed");  //֪ͨ����˹رն�Ӧ��SocketThead
						if( JOptionPane.showConfirmDialog(pnlMain,
								"�Ƿ�رտͻ��ˣ�", "����", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
							LoginFrame frame = new LoginFrame();
							frame.setVisible(true);
						} 
						this.dispose();
						break;  // �����߳�
					}
					//---
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try{
					if (socket != null)
						socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
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
