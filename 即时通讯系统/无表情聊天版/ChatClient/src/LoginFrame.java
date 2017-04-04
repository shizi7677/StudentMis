

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private LoginFrame loginFrame;
	
	private JPanel pnlMain;
	private JLabel lblLoginName;
	private JLabel lblPassword;
	private JTextField txtLoginName ;   
	private JPasswordField txtPassword ;
	private JButton btnLogin ;
	private JButton btnCancel;

	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public LoginFrame() {
		loginFrame = this;
		this.init();
	}


	private void init(){
		this.setSize(new Dimension(350, 220));
		this.setLocationRelativeTo(null);  //����
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnlMain =new JPanel();
		this.setContentPane(pnlMain);
		pnlMain.setLayout(null);

		this.lblLoginName = new JLabel("��¼����");
		this.txtLoginName = new JTextField();
		this.lblPassword = new JLabel("���룺");
		this.txtPassword = new JPasswordField();
		this.btnLogin = new JButton("��¼");
		this.btnCancel = new JButton("ȡ��");

		lblLoginName.setBounds(10, 33, 60, 30);
		txtLoginName.setBounds(150, 30, 150, 30);
		lblPassword.setBounds(10, 80, 60, 30);
		txtPassword.setBounds(150, 80,150, 30);
		btnLogin.setBounds(80, 135, 60, 25);
		btnCancel.setBounds(200, 135, 60, 25);



		this.btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtLoginName.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "��¼������Ϊ��!", "��ʾ��Ϣ",   JOptionPane.INFORMATION_MESSAGE);
					txtLoginName.requestFocus();
				} else if(txtPassword.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "���벻��Ϊ��!", "��ʾ��Ϣ",   JOptionPane.INFORMATION_MESSAGE);
					txtPassword.requestFocus();
				} else {
					try {
						if(socket == null){
							socket = new Socket("127.0.1.1",9028);
						}
						if(dis == null){
							dis = new DataInputStream(socket.getInputStream());
						}
						if(dos == null){
							dos = new DataOutputStream(socket.getOutputStream());
						}
						
						String s = String.format("Login:%s:%s", txtLoginName.getText(),txtPassword.getText());
						dos.writeUTF(s);
						dos.flush();
						
						String str = dis.readUTF();  // --- �߳�����
						if(str.compareTo("Failed") == 0) {
							JOptionPane.showMessageDialog(pnlMain, "�û������������","����",JOptionPane.WARNING_MESSAGE);
						} else if(str.compareTo("Successed") == 0) {
							new MainFrame(socket,txtLoginName.getText());
							loginFrame.dispose();
						} else if(str.compareTo("Refused") == 0){
							JOptionPane.showMessageDialog(pnlMain, "���û��ѵ�¼,�벻Ҫ�ظ���½��","����",JOptionPane.WARNING_MESSAGE);
						}
					} catch (Exception e1) {
						if (e1.getMessage().indexOf("Connection refused: connect") >= 0)
							JOptionPane.showMessageDialog(pnlMain, "������ά���У����Ժ��½��","����",JOptionPane.WARNING_MESSAGE);
						else{
							e1.printStackTrace();
						}
					}  
				}

			}
		});

		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtLoginName.setText("");
				txtPassword.setText("");
			}
		});

 


		pnlMain.add(lblLoginName); 
		pnlMain.add(txtLoginName); 
		pnlMain.add(lblPassword);
		pnlMain.add(txtPassword); 
		pnlMain.add(btnLogin);     
		pnlMain.add(btnCancel ); 
		this.setVisible(true);

	}


}
