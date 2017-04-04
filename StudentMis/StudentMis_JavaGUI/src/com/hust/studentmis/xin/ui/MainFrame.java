package com.hust.studentmis.xin.ui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame {
 
	private static final long serialVersionUID = 1L;
	
	private JPanel pnlMain = null;
	private String[] menuNames = {
			"ѧ����Ϣ����", "��ʦ��Ϣ����", "�γ���Ϣ����",
			"�༶��Ϣ����", "ѡ�ι���", "�ڿι���"};
	private JMenuItem[] menuItems = null; //�˵���
	private JMenuBar menuBar = null; //�˵���
	private JDesktopPane desktopPane = null; //������ʽ�Ӵ��������

	private JInternalFrame manageStudentFrame = null;
	private JInternalFrame manageTeacherFrame = null;
	private JInternalFrame manageClazzFrame = null;
	private JInternalFrame manageCourseFrame = null;
	private JInternalFrame manageStudyCourseFrame = null;
	private JInternalFrame manageTeachCourseFrame = null;



	public MainFrame() throws HeadlessException {
		init();
	}

	private void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		this.setTitle("ѧ����Ϣ����ϵͳ");

		//��ʼ����Ա
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		pnlMain = new JPanel();
		this.setContentPane(pnlMain);
		pnlMain.setLayout(new BorderLayout());
		desktopPane = new JDesktopPane();
		pnlMain.add(desktopPane, BorderLayout.CENTER);

		//���ò˵�������
		menuItems = new JMenuItem[menuNames.length];
		for (int i = 0; i < menuNames.length; i++) {
			menuItems[i] = new JMenuItem(menuNames[i]);
			menuBar.add(menuItems[i]);
		}
		
		//���ò˵��������
		menuItems[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manageStudentFrame == null) {
					manageStudentFrame = new ManageStudentFrame();
				}
				desktopPane.add(manageStudentFrame);
				manageStudentFrame.setVisible(true);
			}
		});
		menuItems[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manageTeacherFrame == null) {
					manageTeacherFrame = new ManageCourseFrame();
				}
				desktopPane.add(manageTeacherFrame);
				manageTeacherFrame.setVisible(true);
			}
		});
		menuItems[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manageCourseFrame == null) {
//					manageCourseFrame = new ManageCourseFrame();
					manageCourseFrame = new ManageTest();
				}
				desktopPane.add(manageCourseFrame);
				manageCourseFrame.setVisible(true);
			}
		});
		menuItems[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manageClazzFrame == null) {
					manageClazzFrame = new ManageClazzFrame();
				}
				desktopPane.add(manageClazzFrame);
				manageClazzFrame.setVisible(true);
			}
		});
		menuItems[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manageStudyCourseFrame == null) {
					manageStudyCourseFrame = new ManageStudyCourseFrame();
				}
				desktopPane.add(manageStudyCourseFrame);
				manageStudyCourseFrame.setVisible(true);
			}
		});
		menuItems[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manageTeachCourseFrame == null) {
					manageTeachCourseFrame = new ManageCourseFrame();
				}
				desktopPane.add(manageTeachCourseFrame);
				manageTeachCourseFrame.setVisible(true);
			}
		});

		this.setVisible(true);
	}
}
