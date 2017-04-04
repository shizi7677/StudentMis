package com.hust.studentmis.xin.ui;


import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.hust.studentmis.xin.business.ClazzManager;
import com.hust.studentmis.xin.business.CourseManager;
import com.hust.studentmis.xin.business.StudentManager;
import com.hust.studentmis.xin.business.StudyCourseManager;
import com.hust.studentmis.xin.entity.ClazzBean;
import com.hust.studentmis.xin.entity.CourseBean;
import com.hust.studentmis.xin.entity.StudentBean;
import com.hust.studentmis.xin.entity.StudyCourseBean;


public class ManageStudyCourseFrame extends JInternalFrame {
	
	private StudyCourseManager manager = new StudyCourseManager();
	private StudyCourseBean studyCourse;
	
	private JSplitPane splPanel;  //带分割条的面板
	private JPanel pnlMain;
	private JPanel pnlLeft;
	private JPanel pnlCenter;
	
	
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
 

	private JTextField txt1 ;   
   
	private JTextField txt4 ;  
	private JTextField txt5; 
 
	
	private JComboBox cmb1;//学生名称
	private JComboBox cmb2;//课程名称
	

	private JButton btnAdd ;
	private JButton btnCancel;
 

	private JScrollPane scrollPane = new JScrollPane();
	private JTable tabMain = null;

	//设置控件位置
	int btnWidth = 20;
	int btnHeight = 20;
	int lblSpaceY = 3;    //垂直间隔
	int lblStartX = 30;
	int lblWidth = 100;
	int txtStartX = lblStartX + lblWidth + 7;
	int txtWidth = 120;
	int lblStartY = 15;
	int lblHeight = 40;
	int txtStartY = lblStartY + 10;
	int txtHeight = 20;

	public ManageStudyCourseFrame() throws HeadlessException {
		super("", true, true, true, true);
		init();
	}

	private void init() {
 		this.setSize(new Dimension(480, 460));// 设置尺寸
		this.setResizable(true);
		
		/*
		 * 左面板
		 */
		pnlLeft = new JPanel();
		pnlLeft.setLayout(null);	// 没有使用布局，需要逐一指定组件位置
		
		this.lbl1 = new JLabel("选课编号：");
		this.lbl2 = new JLabel("学生名称：");
		this.lbl3 = new JLabel("课程名称：");
		this.lbl4 = new JLabel("课程成绩：");
		this.lbl5 = new JLabel("考试时间：");
 
		this.txt1 = new JTextField();
	 
		this.txt4 = new JTextField();
		this.txt5 = new JTextField();
 
		
		this.btnAdd = new JButton("添加");
		this.btnCancel = new JButton("取消");
		
 
		//学生下拉列表框
		List<StudentBean> students = new StudentManager().display();
		Object[] objs1 = new Object[students.size() + 1];
		objs1[0] = "-请选择-";
		for (int j = 1; j < objs1.length; j++) {
			objs1[j] = students.get(j - 1).getStudentNumber() + ":" + students.get(j - 1).getStudentName();
		}
		cmb1 = new JComboBox(objs1);
		

		//课程下拉列表框
		List<CourseBean> courses = new CourseManager().display();
		Object[] objs2 = new Object[courses.size() + 1];
		objs2[0] = "-请选择-";
		for (int j = 1; j < objs2.length; j++) {
			objs2[j] = courses.get(j - 1).getCourseNumber() + ":" + courses.get(j - 1).getCourseName();
		}
		cmb2 = new JComboBox(objs2);
		

		
		
		int i = 1;
		lbl1.setBounds(lblStartX, lblStartY, lblWidth, lblHeight);
		txt1.setBounds(txtStartX, txtStartY, txtWidth, txtHeight); 
		lbl2.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		cmb1.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);
		
		lbl3.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		cmb2.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);
		
		lbl4.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		txt4.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);
		
		lbl5.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		txt5.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);
		
		btnAdd.setBounds(80, 350, 60, 25);
		btnCancel.setBounds(200, 350, 60, 25);

		//下拉框监听
		cmb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pnlMain, 
						cmb1.getSelectedItem().toString(), "标题", JOptionPane.INFORMATION_MESSAGE );
			}
		}); 
		cmb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pnlMain, 
						cmb2.getSelectedItem().toString(), "标题", JOptionPane.INFORMATION_MESSAGE );
			}
		}); 
 		//限制只能输入数字
		KeyListener keyListener = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();				
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

				}else{
					e.consume(); //关键，屏蔽掉非法输入
				}
			}
		};
		lbl4.addKeyListener(keyListener);
		//添加验证
		this.btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txt1.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "选课编号不能为空", "提示消息",   JOptionPane.INFORMATION_MESSAGE);
					txt1.requestFocus();
				}  else  if(txt4.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "课程成绩不能为空", "提示消息",   JOptionPane.INFORMATION_MESSAGE);
					txt4.requestFocus();
				} else  if(txt5.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "考试时间不能为空", "提示消息",   JOptionPane.INFORMATION_MESSAGE);
					txt5.requestFocus();
				}
			 
				
				
				else   {
					studyCourse = new StudyCourseBean();
				
					studyCourse.setStudyCourseNumber(txt1.getText());
					studyCourse.setScore(Float.parseFloat(txt4.getText()));
                    studyCourse.setExamDate( Date.valueOf(txt5.getText()));					
				 
					studyCourse.setStudentNumber(cmb1.getSelectedItem().toString());
					studyCourse.setCourseNumber(cmb2.getSelectedItem().toString());

					if(manager.add(studyCourse)) {
						JOptionPane.showMessageDialog(pnlMain, 
								"添加成功。", "标题", JOptionPane.INFORMATION_MESSAGE );
						clearTextContent();
					} else {
						JOptionPane.showMessageDialog(pnlMain, 
								"添加失败。", "标题", JOptionPane.WARNING_MESSAGE );
					}
					showData(); //刷新数据
				}
			}
		});
		//取消
		this.btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearTextContent();
			}

		});
		//关闭子窗口
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				e.getInternalFrame().setVisible(false);
			}
		});

		pnlLeft.add(lbl1); 
		pnlLeft.add(txt1); 
		pnlLeft.add(lbl2);
		pnlLeft.add(lbl3);
		pnlLeft.add(lbl4);	
		pnlLeft.add(txt4); 
		pnlLeft.add(lbl5);
		pnlLeft.add(txt5); 
		
		pnlLeft.add(cmb1);
		pnlLeft.add(cmb2);
		
		pnlLeft.add(btnAdd);     
		pnlLeft.add(btnCancel ); 
		
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
		List<StudyCourseBean> studyCourses = manager.display();
		Object [][] datas = new Object[studyCourses.size()][7];
		String [] colName = {"选课编号", "课程名称","学生名称", "课程成绩", "考试时间"};

		for (int i = 0; i < studyCourses.size(); i++) {
			datas[i][0] = studyCourses.get(i).getStudyCourseNumber();
			datas[i][1] = studyCourses.get(i).getCourse().getCourseName();
			datas[i][2] = studyCourses.get(i).getStudent().getStudentName();
			datas[i][3] = studyCourses.get(i).getScore();
			datas[i][4] = studyCourses.get(i).getExamDate();
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
	
	
 
	public void clearTextContent() {
		txt1.setText("");
		txt4.setText("");
		txt5.setText("");
		
		
		cmb1.setSelectedItem("-请选择-");
		cmb2.setSelectedItem("-请选择-");
	}

}
