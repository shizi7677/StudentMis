package com.hust.studentmis.xin.ui;


import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.hust.studentmis.xin.business.ClazzManager;
import com.hust.studentmis.xin.business.CourseManager;
import com.hust.studentmis.xin.business.StudentManager;
import com.hust.studentmis.xin.entity.ClazzBean;
import com.hust.studentmis.xin.entity.CourseBean;
import com.hust.studentmis.xin.entity.StudentBean;


public class ManageStudentFrame extends JInternalFrame {
	
	private StudentManager manager = new StudentManager();
	private StudentBean student;
	
	private JSplitPane splPanel;  //���ָ��������
	private JPanel pnlMain;
	private JPanel pnlLeft;
	private JPanel pnlCenter;
	
	
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JLabel lbl6;
	private JLabel lbl7;

	private JTextField txt1 ;   
	private JTextField txt2 ;  
	private JTextField txt3 ;  
	private JTextField txt4 ;  
	private JTextField txt5; 
	private JTextField txt6 ; 
	
	private JComboBox cmb1;
	private JLabel lblMan;
	private JLabel lblWoman;
	 

	private JButton btnAdd ;
	private JButton btnCancel;
	private JRadioButton btnMan;
	private JRadioButton btnWoman;
	private ButtonGroup group;

	private JScrollPane scrollPane = new JScrollPane();
	private JTable tabMain = null;

	//���ÿؼ�λ��
	int btnWidth = 20;
	int btnHeight = 20;
	int lblSpaceY = 3;    //��ֱ���
	int lblStartX = 30;
	int lblWidth = 100;
	int txtStartX = lblStartX + lblWidth + 7;
	int txtWidth = 120;
	int lblStartY = 15;
	int lblHeight = 40;
	int txtStartY = lblStartY + 10;
	int txtHeight = 20;

	public ManageStudentFrame() throws HeadlessException {
		super("", true, true, true, true);
		init();
	}

	private void init() {
 		this.setSize(new Dimension(480, 460));// ���óߴ�
		this.setResizable(true);
		
		/*
		 * �����
		 */
		pnlLeft = new JPanel();
		pnlLeft.setLayout(null);	// û��ʹ�ò��֣���Ҫ��һָ�����λ��
		
		this.lbl1 = new JLabel("ѧ����ţ�");
		this.lbl2 = new JLabel("ѧ�����ƣ�");
		this.lbl3 = new JLabel("ѧ���Ա�");
		this.lbl4 = new JLabel("ѧ�����գ�");
		this.lbl5 = new JLabel("ѧ����ϵ��ʽ��");
		this.lbl6 = new JLabel("ѧ����ַ��");
		this.lbl7 = new JLabel("ѧ���༶��");
		this.txt1 = new JTextField();
		this.txt2 = new JTextField();
		this.txt3 = new JTextField();
		this.txt4 = new JTextField();
		this.txt5 = new JTextField();
		this.txt6  = new JTextField() ;  
		this.btnAdd = new JButton("���");
		this.btnCancel = new JButton("ȡ��");
		
		lblMan = new JLabel("��");
		lblWoman = new JLabel("Ů");
		
		btnMan = new JRadioButton("��", true);
		btnWoman = new JRadioButton("Ů");
		group = new ButtonGroup();
		group.add(btnMan);
		group.add(btnWoman);

//		//����ֻ����������
//		KeyListener keyListener = new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				int keyChar = e.getKeyChar();				
//				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
//
//				}else{
//					e.consume(); //�ؼ������ε��Ƿ�����
//				}
//			}
//		};
//		
		
		
		
		//�༶�����б��
		List<ClazzBean> clazzes = new ClazzManager().display();
		Object[] objs = new Object[clazzes.size() + 1];
		objs[0] = "-��ѡ��-";
		for (int j = 1; j < objs.length; j++) {
			objs[j] = clazzes.get(j - 1).getClazzNumber() + ":" + clazzes.get(j - 1).getClazzName();
		}
		cmb1 = new JComboBox(objs);
		
		cmb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pnlMain, 
						cmb1.getSelectedItem().toString(), "����", JOptionPane.INFORMATION_MESSAGE );
			}
		}); 
		
		
		int i = 1;
		lbl1.setBounds(lblStartX, lblStartY, lblWidth, lblHeight);
		txt1.setBounds(txtStartX, txtStartY, txtWidth, txtHeight); 
		lbl2.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		txt2.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);
		
		lbl3.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		this.btnMan.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i, btnWidth, btnHeight);
		lblMan.setBounds(txtStartX + btnHeight + 3, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		this.btnWoman.setBounds(txtStartX + 60, txtStartY + (lblHeight + lblSpaceY) * i, btnWidth, btnHeight);
		lblWoman.setBounds(txtStartX + btnHeight + 63 , lblStartY + (lblHeight + lblSpaceY) * i++, lblWidth, lblHeight) ;
		
		lbl4.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		txt4.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);
		
		lbl5.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		txt5.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);
		
		lbl6.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		txt6.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);
		
		lbl7.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		cmb1.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);

		btnAdd.setBounds(80, 350, 60, 25);
		btnCancel.setBounds(200, 350, 60, 25);

		
		//�����֤
		this.btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txt1.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "ѧ����Ų���Ϊ��", "��ʾ��Ϣ",   JOptionPane.INFORMATION_MESSAGE);
					txt1.requestFocus();
				} else if(txt2.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "ѧ����������Ϊ��", "��ʾ��Ϣ",   JOptionPane.INFORMATION_MESSAGE);
					txt2.requestFocus();
				} else  if(txt3.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "ѧ���Ա���Ϊ��", "��ʾ��Ϣ",   JOptionPane.INFORMATION_MESSAGE);
					txt3.requestFocus();
				} else  if(txt4.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "ѧ�����ղ���Ϊ��", "��ʾ��Ϣ",   JOptionPane.INFORMATION_MESSAGE);
					txt4.requestFocus();
				} else  if(txt5.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "ѧ����ϵ��ʽ����Ϊ��", "��ʾ��Ϣ",   JOptionPane.INFORMATION_MESSAGE);
					txt5.requestFocus();
				} else  if(txt6.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "ѧ����ַ����Ϊ��", "��ʾ��Ϣ",   JOptionPane.INFORMATION_MESSAGE);
					txt6.requestFocus();
				}  else {
					student = new StudentBean();
					student.setStudentNumber(txt1.getText());
					student.setStudentName(txt2.getText());
					student.setGender(txt3.getText());
					//					student.setBirthday(txt4.getText());
					student.setPhoneNumber(txt5.getText());
					student.setAddress(txt6.getText());
					student.setClazzNumber(cmb1.getSelectedItem().toString());

					if(manager.add(student)) {
						JOptionPane.showMessageDialog(pnlMain, 
								"��ӳɹ���", "����", JOptionPane.INFORMATION_MESSAGE );
						clearTextContent();
					} else {
						JOptionPane.showMessageDialog(pnlMain, 
								"���ʧ�ܡ�", "����", JOptionPane.WARNING_MESSAGE );
					}
					showData(); //ˢ������
				}
			}
		});


		this.btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearTextContent();
			}

		});
		
		//�ر��Ӵ���
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				e.getInternalFrame().setVisible(false);
			}
		});

		pnlLeft.add(lbl1); 
		pnlLeft.add(txt1); 
		pnlLeft.add(lbl2);
		pnlLeft.add(txt2); 
		pnlLeft.add(lbl3);
		pnlLeft.add(btnMan);
		pnlLeft.add(btnWoman);
		pnlLeft.add(lbl4);	
		pnlLeft.add(txt4); 
		pnlLeft.add(lbl5);
		pnlLeft.add(txt5); 
		pnlLeft.add(lbl6);	
		pnlLeft.add(txt6); 
		pnlLeft.add(lbl7);	
		
		pnlLeft.add(cmb1);
		pnlLeft.add(lblMan);
		pnlLeft.add(lblWoman);
		
		pnlLeft.add(btnAdd);     
		pnlLeft.add(btnCancel ); 
		
		/**
		 * �Ҳ����
		 */
		pnlCenter = new JPanel();
		splPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,pnlLeft,pnlCenter);
		splPanel.setDividerLocation(0.3);  //�ָ����
		splPanel.setOneTouchExpandable(true); //����JSplitPane�Ƿ����չ��������(��ͬ�ļ��ܹ�һ��)����Ϊtrue��ʾ�򿪴˹��ܡ�
		splPanel.setDividerSize(10);//���÷ָ��߿�ȵĴ�С����pixelΪ���㵥λ��
		
		showData();
		this.setContentPane(splPanel);
		this.setVisible(true);	// ��ʾ
	}

	
	private void showData() {
		// ��ʼ��Model��Ϣ
		List<StudentBean> students = manager.display();
		Object [][] datas = new Object[students.size()][7];
		String [] colName = {"ѧ�����", "�༶����","ѧ������", "�Ա�", "����", "��ϵ��ʽ","��ͥסַ"};
		
		for (int i = 0; i < students.size(); i++) {
			datas[i][0] = students.get(i).getStudentNumber ();
			datas[i][1] = students.get(i).getClazz().getClazzName();
			datas[i][2] = students.get(i).getStudentName();
			datas[i][3] = students.get(i).getGender();
			datas[i][4] = students.get(i).getBirthday();
			datas[i][5] = students.get(i).getPhoneNumber();
			datas[i][6] = students.get(i).getAddress();
		}
		// ��ModelΪ��������View
		tabMain = new JTable(datas, colName);
		
		tabMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { 
				int sr;
				if ((sr = tabMain.getSelectedRow()) == -1) {
					return;
				}
			}
		});
		
		scrollPane.getViewport().add(tabMain); // JTable����ֱ��add()��Panel��
		pnlCenter.add(scrollPane);
	}
	
	
//	private class GenderListener implements ActionListener {
//		//���ݰ��µĵ�ѡ��ť���ñ�ǩ������
//		public void actionPerformed(ActionEvent e) {
//			Object source = e.getSource();
//			if(source == btnMan)
//				btnMan.setText("��");
//			else if(source == btnWoman)
//				btnWoman.setText("Ů");
//		}
//	}
	
	public void clearTextContent() {
		txt1.setText("");
		txt2.setText("");
		txt3.setText("");
		txt4.setText("");
		txt5.setText("");
		txt6.setText("");
	}

}
