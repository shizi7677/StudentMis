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
	private JSplitPane splPanel = null;  //带分割条的面板
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
		this.setSize(new Dimension(480, 460));	// 设置尺寸
		this.setResizable(true);
		
		
		/*
		 * 左面板
		 */
		pnlLeft = new JPanel();
		pnlLeft.setLayout(null);	// 没有使用布局，需要逐一指定组件位置
		
		JLabel lblCourseNumber = new JLabel("课程编号", JLabel.RIGHT);
		lblCourseNumber.setBounds(fillWidth, fillHeight, compWidth, compHeight);	// 设置组件位置
		JLabel lblCourseName = new JLabel("课程名称", JLabel.RIGHT);
		lblCourseName.setBounds(fillWidth, fillHeight * 2 + compHeight, compWidth, compHeight);
		JLabel lblCredit = new JLabel("学分", JLabel.RIGHT);
		lblCredit.setBounds(fillWidth, fillHeight * 3 + compHeight * 2, compWidth, compHeight);
		JLabel lblHours = new JLabel("课时", JLabel.RIGHT);
		lblHours.setBounds(fillWidth, fillHeight * 4 + compHeight * 3, compWidth, compHeight);
		JLabel lblDescription = new JLabel("课程描述", JLabel.RIGHT);
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
		objs[0] = "-请选择-";
		for (int i = 1; i < objs.length; i++) 
			objs[i] = courses.get(i - 1).getCourseNumber() + ":" + courses.get(i - 1).getCourseName();
		
		cmbCourses = new JComboBox(objs);
		cmbCourses.setBounds(fillWidth * 2 + compWidth, fillHeight * 6 + compHeight * 7, compWidth * 2, compHeight);
		cmbCourses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pnlMain, 
						cmbCourses.getSelectedItem().toString(), "标题", JOptionPane.INFORMATION_MESSAGE );
			}
		});
		
		JButton btnAdd = new JButton("添加");
		btnAdd.setBounds(44, fillHeight * 7 + compHeight * 8, compWidth, compHeight);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtCourseNumber.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"课程编号不能为空", "标题", JOptionPane.INFORMATION_MESSAGE );
					txtCourseNumber.requestFocus();
				}
				else if (txtCourseName.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"课程名称不能为空", "标题", JOptionPane.INFORMATION_MESSAGE );
					txtCourseName.requestFocus();
				}
				else if (txtCredit.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"学分不能为空", "标题", JOptionPane.INFORMATION_MESSAGE );
					txtCredit.requestFocus();
				}
				// ... 非空验证
				else {
					CourseBean course = new CourseBean();
					course.setCourseNumber(txtCourseNumber.getText());
					course.setCourseName(txtCourseName.getText());

					try {
						course.setCredit(Float.parseFloat(txtCredit.getText()));
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(pnlMain, 
								"学分必须是数字", "标题", JOptionPane.INFORMATION_MESSAGE );
						txtCredit.setText("");
						txtCredit.requestFocus();
					}
					course.setHours(Float.parseFloat(txtHours.getText()));
					course.setDescription(txtDescription.getText());

					try {
						if (new CourseManager().add(course)) { 	// 不能判断 == 1，考虑insert触发器问题
							JOptionPane.showMessageDialog(pnlMain, 
									"添加成功。", "标题", JOptionPane.INFORMATION_MESSAGE );
						}
						else {
							JOptionPane.showMessageDialog(pnlMain, 
									"添加失败。", "标题", JOptionPane.WARNING_MESSAGE );
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(pnlMain, 
								ex.getMessage(), "标题", JOptionPane.ERROR_MESSAGE );
					}
					
					showData();
				}
			}
		});
		
		JButton btnCancel = new JButton("取消");
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
		 * 右侧面板
		 */
		pnlCenter = new JPanel();
		splPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,pnlLeft,pnlCenter);
		
		splPanel.setDividerLocation(0.3);  //分割比例
		splPanel.setOneTouchExpandable(true); //设置JSplitPane是否可以展开或收起(如同文件总管一般)，设为true表示打开此功能。
		splPanel.setDividerSize(10);//设置分隔线宽度的大小，以pixel为计算单位。
		
		showData();
		this.setContentPane(splPanel);
		this.setVisible(true);	// 显示
	}

	
	private void showData() {
		// 初始化Model信息
		List<CourseBean> courses = new CourseManager().display();
		Object [][] datas = new Object[courses.size()][5];
		String [] colName = {"课程编号", "课程名称", "学分", "课时", "描述"};
		
		for (int i = 0; i < courses.size(); i++) {
			datas[i][0] = courses.get(i).getCourseNumber();
			datas[i][1] = courses.get(i).getCourseName();
			datas[i][2] = courses.get(i).getCredit();
			datas[i][3] = courses.get(i).getHours();
			datas[i][4] = courses.get(i).getDescription();
		}
		// 以Model为参数创建View
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
		
		scrollPane.getViewport().add(tabMain); // JTable不能直接add()到Panel中
		pnlCenter.add(scrollPane);
	}
	
	

}
