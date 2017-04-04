
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JTextArea;

import com.neuedu.guomy.business.LoginManager;


/**
 * 
 * �ڷ����ר������ͬһ���ͻ��˽������̣߳�
 * @author ttc
 *
 */
public class SocketThread implements Runnable {

	private Timestamp now = new Timestamp(System.currentTimeMillis());	// ʱ��

	Socket socket;
	JTextArea txtContent;
	List<SocketThread> socketThreads;
	String name;
	MainFrame mainFrame;
	DataOutputStream dos;

	String outStr;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
	public SocketThread(Socket socket, JTextArea txtContent,
			List<SocketThread> socketThreads) {
		super();
		this.socket = socket;
		this.txtContent = txtContent;
		this.socketThreads = socketThreads;
	}

	public SocketThread(Socket socket, JTextArea txtContent,
			List<SocketThread> socketThreads, MainFrame mainFrame) {
		super();
		this.socket = socket;
		this.txtContent = txtContent;
		this.socketThreads = socketThreads;
		this.mainFrame = mainFrame;

	}
	@Override
	public void run() {
		DataInputStream dis = null;
		if (socket != null) { 
			try {
				dis = new DataInputStream(socket.getInputStream());
				while (true) {
					String str = dis.readUTF();	// �̹߳���

					txtContent.setText(str + ":" + now.toString() + "\n" + txtContent.getText());	// ���� txtContent

					String [] strs = str.split(":");
					//�����û���½
					if (strs[0].compareToIgnoreCase("Login") == 0) {
						if (new LoginManager().isLogin(strs[1], strs[2]) != null) {
							boolean canLogin = true;
							if(socketThreads != null) {
								for (SocketThread socketThread : socketThreads) {
									if(socketThread.getName() != null) {
										if (socketThread.getName().compareTo(strs[1]) == 0) {
											canLogin = false;
										}
									}
								}
							}

							if(canLogin) {
								// ��ȡ�ͻ��ĵ�½��
								name = strs[1];
								//���ص�½�ɹ���Ϣ
								writeData("Successed");
								this.mainFrame.showUsers();
								//��ÿͻ��˷������½ǰ�������û��б�
								String listStr = "UserList";
								for (int i = 0; i < socketThreads.size(); i++){
									listStr += ":" + socketThreads.get(i).getName();
								}
								writeData(listStr);

								//��ÿͻ��˷��ͣ����ÿͻ����Լ����Ҫ�����û��б����Ϣ
								for (SocketThread socketThread : socketThreads) {
									if (socketThread.getName().compareTo(name) != 0) {
										outStr = String.format("AddUser:%s:%s",strs[1],now.toString());
										socketThread.writeData(outStr);
									}
								}
							} else {
								writeData("Refused");
							}
						}
						else {
							//���ص�½ʧ����Ϣ
							writeData("Failed");
						}
					}
					//�����û����죺��������Ϣת���������ͻ���
					else if (strs[0].compareToIgnoreCase("Chat") == 0) {
						for (SocketThread socketThread : socketThreads) {
							if (socketThread.getName().compareTo(name) == 0 ||
									socketThread.getName().compareTo(strs[1]) == 0 
							) {
								//Chat:���ͷ������ݣ�ʱ��
								outStr = String.format("Chat:%s:%s:%s",name,strs[2],now.toString());
								socketThread.writeData(outStr);
							}
						}
					}
					else if (strs[0].compareToIgnoreCase("ChatAll") == 0) {
						for (SocketThread socketThread : socketThreads) {
							//ChatAll:���ͷ������ݣ�ʱ��
							outStr = String.format("ChatAll:%s:%s:%s",name,strs[1],now.toString());
							socketThread.writeData(outStr);
						}
					}
					//�����û����ߣ���������Ϣת���������ͻ���
					else if (str.compareToIgnoreCase("Client_Request_Exit") == 0) {
						for (SocketThread socketThread : socketThreads) {
							if (socketThread.getName().compareTo(name) != 0) {
								outStr = String.format("RemoveUser:%s:%s", name, now.toString());
								socketThread.writeData(outStr);  //֪ͨ�����ͻ��ˣ��Ƴ����߿ͻ�
							}  
						}

						for (SocketThread socketThread : socketThreads) {
							if (socketThread.getName().compareTo(name) == 0) {
								socketThread.writeData("Server_Agree_Exit");
								socketThreads.remove(socketThread); //�����û��ӷ�����û��б���ɾ��
								break;
							}  
						}
						mainFrame.showUsers();  //ˢ�·���˵��û��б�
						break;
					}
					//�����˹رն����µĿͻ��˹رգ������ر�SocketThread
					else if (strs[0].compareToIgnoreCase("ClientClosed") == 0) {
//						System.out.println("���ڹر�SocketThread!");
						break;
					}
					else if (strs[0].compareToIgnoreCase("ServerStop") == 0) {
//						System.out.println("���ڹر����һ�����ٷ���˵�socket!");
						break;
					}

				}
			} catch (IOException e) {

			} finally {
				try{
					if(dos != null) {
						dos.close();
					}
					if(socket != null) {
						socket.close();
					}
				} catch(IOException e) {
					e.printStackTrace();
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
