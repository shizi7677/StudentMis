package com.hust.studentmis.xin.ui;


import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.hust.studentmis.xin.business.CourseManager;
import com.hust.studentmis.xin.entity.CourseBean;


public class ManageCourseFrame extends JInternalFrame {
	private JSplitPane splPanel = null;  //���ָ��������
	private JPanel pnlMain = null;
	private JPanel pnlLeft = null;
	private JPanel pnlCenter = null;
	private JTextField txtCourseNumber = null;
	private JTextField txtCourseName = null;
	private JTextField txtCredit = null;
	private JTextField txtHours = null;
	private JTextArea txtDescription = null;
	private JComboBox cmbCourses = null;

	private JScrollPane scrollPane = new JScrollPane();
	private JTable tabMain = null;

	private int fillWidth = 10;
	private int fillHeight = 10;
	private int compWidth = 60;
	private int compHeight = 20;

	public ManageCourseFrame() throws HeadlessException {
		super("", true, true, true, true);
		init();
	}

	private void init() {
		this.setSize(new Dimension(480, 460));	// ���óߴ�
		this.setResizable(true);
		
		
		/*
		 * �����
		 */
		pnlLeft = new JPanel();
		pnlLeft.setLayout(null);	// û��ʹ�ò��֣���Ҫ��һָ�����λ��
		
		JLabel lblCourseNumber = new JLabel("�γ̱��", JLabel.RIGHT);
		lblCourseNumber.setBounds(fillWidth, fillHeight, compWidth, compHeight);	// �������λ��
		JLabel lblCourseName = new JLabel("�γ�����", JLabel.RIGHT);
		lblCourseName.setBounds(fillWidth, fillHeight * 2 + compHeight, compWidth, compHeight);
		JLabel lblCredit = new JLabel("ѧ��", JLabel.RIGHT);
		lblCredit.setBounds(fillWidth, fillHeight * 3 + compHeight * 2, compWidth, compHeight);
		JLabel lblHours = new JLabel("��ʱ", JLabel.RIGHT);
		lblHours.setBounds(fillWidth, fillHeight * 4 + compHeight * 3, compWidth, compHeight);
		JLabel lblDescription = new JLabel("�γ�����", JLabel.RIGHT);
		lblDescription.setBounds(fillWidth, fillHeight * 5 + compHeight * 4, compWidth, compHeight);

		txtCourseNumber = new JTextField();
		txtCourseNumber.setBounds(fillWidth * 2 + compWidth, fillHeight, compWidth * 2, compHeight);
		txtCourseName = new JTextField();
		txtCourseName.setBounds(fillWidth * 2 + compWidth, fillHeight * 2 + compHeight, compWidth * 2, compHeight);
		
		KeyListener keyListener = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// txtDescription.setText("keyTyped\n" + txtDescription.getText());
				if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || 
						e.getKeyChar() == '\b' || e.getKeyChar() == '.'))
					e.consume();
			}
		};
		
		txtCredit = new JTextField();
		txtCredit.setBounds(fillWidth * 2 + compWidth, fillHeight * 3 + compHeight * 2, compWidth * 2, compHeight);
		txtCredit.addKeyListener(keyListener);
		
		txtHours = new JTextField();
		txtHours.setBounds(fillWidth * 2 + compWidth, fillHeight * 4 + compHeight * 3, compWidth * 2, compHeight);
		txtHours.addKeyListener(keyListener);
		
		txtDescription = new JTextArea();
		txtDescription.setBounds(fillWidth * 2 + compWidth, fillHeight * 5 + compHeight * 4, compWidth * 3, compHeight * 3);
		
		List<CourseBean> courses = new CourseManager().display();
		Object [] objs = new Object[courses.size() + 1];
		objs[0] = "-��ѡ��-";
		for (int i = 1; i < objs.length; i++) 
			objs[i] = courses.get(i - 1).getCourseNumber() + ":" + courses.get(i - 1).getCourseName();
		
		cmbCourses = new JComboBox(objs);
		cmbCourses.setBounds(fillWidth * 2 + compWidth, fillHeight * 6 + compHeight * 7, compWidth * 2, compHeight);
		cmbCourses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pnlMain, 
						cmbCourses.getSelectedItem().toString(), "����", JOptionPane.INFORMATION_MESSAGE );
			}
		});
		
		JButton btnAdd = new JButton("���");
		btnAdd.setBounds(44, fillHeight * 7 + compHeight * 8, compWidth, compHeight);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtCourseNumber.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"�γ̱�Ų���Ϊ��", "����", JOptionPane.INFORMATION_MESSAGE );
					txtCourseNumber.requestFocus();
				}
				else if (txtCourseName.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"�γ����Ʋ���Ϊ��", "����", JOptionPane.INFORMATION_MESSAGE );
					txtCourseName.requestFocus();
				}
				else if (txtCredit.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"ѧ�ֲ���Ϊ��", "����", JOptionPane.INFORMATION_MESSAGE );
					txtCredit.requestFocus();
				}
				// ... �ǿ���֤
				else {
					CourseBean course = new CourseBean();
					course.setCourseNumber(txtCourseNumber.getText());
					course.setCourseName(txtCourseName.getText());

					try {
						course.setCredit(Float.parseFloat(txtCredit.getText()));
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(pnlMain, 
								"ѧ�ֱ���������", "����", JOptionPane.INFORMATION_MESSAGE );
						txtCredit.setText("");
						txtCredit.requestFocus();
					}
					course.setHours(Float.parseFloat(txtHours.getText()));
					course.setDescription(txtDescription.getText());

					try {
						if (new CourseManager().add(course)) { 	// �����ж� == 1������insert����������
							JOptionPane.showMessageDialog(pnlMain, 
									"��ӳɹ���", "����", JOptionPane.INFORMATION_MESSAGE );
						}
						else {
							JOptionPane.showMessageDialog(pnlMain, 
									"���ʧ�ܡ�", "����", JOptionPane.WARNING_MESSAGE );
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(pnlMain, 
								ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE );
					}
					
					showData();
				}
			}
		});
		
		JButton btnCancel = new JButton("ȡ��");
		btnCancel.setBounds(133, fillHeight * 7 + compHeight * 8, compWidth, compHeight);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtCourseNumber.setText("");
				txtCourseName.setText("");
			}
		});
		
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				e.getInternalFrame().setVisible(false);
			}
		});

		pnlLeft.add(lblCourseNumber);
		pnlLeft.add(lblCourseName);
		pnlLeft.add(lblCredit);
		pnlLeft.add(lblHours);
		pnlLeft.add(lblDescription);
		pnlLeft.add(txtCourseNumber);
		pnlLeft.add(txtCourseName);
		pnlLeft.add(txtCredit);
		pnlLeft.add(txtHours);
		pnlLeft.add(txtDescription);
		pnlLeft.add(cmbCourses);
		pnlLeft.add(btnAdd);
		pnlLeft.add(btnCancel);
		
		
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
		List<CourseBean> courses = new CourseManager().display();
		Object [][] datas = new Object[courses.size()][5];
		String [] colName = {"�γ̱��", "�γ�����", "ѧ��", "��ʱ", "����"};
		
		for (int i = 0; i < courses.size(); i++) {
			datas[i][0] = courses.get(i).getCourseNumber();
			datas[i][1] = courses.get(i).getCourseName();
			datas[i][2] = courses.get(i).getCredit();
			datas[i][3] = courses.get(i).getHours();
			datas[i][4] = courses.get(i).getDescription();
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
	
	

}
