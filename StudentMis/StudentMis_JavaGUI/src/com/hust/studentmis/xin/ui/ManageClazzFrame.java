package com.hust.studentmis.xin.ui;


import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.hust.studentmis.xin.business.ClazzManager;
import com.hust.studentmis.xin.entity.ClazzBean;


public class ManageClazzFrame extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSplitPane splPanel = null;
	private JPanel pnlMain = null;
	private JPanel pnlLeft = null;
	private JPanel pnlCenter = null;
	
	private JTextField txtClassNumber = null;
	private JTextField txtClassName = null;
	private JTextField txtBeginDate = null;
	private JTextField txtEndDate = null;
	private JTextField txt = null;
	
	private JComboBox cmbClazz = null;   //下拉框   

	private JScrollPane scrollPane = new JScrollPane();
	private JTable tabMain = null;
	

	private int fillWidth = 10;
	private int fillHeight = 10;
	private int compWidth = 60;
	private int compHeight = 20;

	
	public ManageClazzFrame() throws HeadlessException {
		super("", true, true, true, true);
		init();
	}
	


	private void init() {
		this.setSize(new Dimension(480, 460));	// 设置尺寸
		this.setResizable(true);
		
		pnlLeft = new JPanel();
		pnlLeft.setLayout(null);	// 没有使用布局，需要逐一指定组件位置

		
		JLabel lblCourseNumber = new JLabel("班级编号", JLabel.RIGHT);
		lblCourseNumber.setBounds(fillWidth, fillHeight, compWidth, compHeight);	// 设置组件位置

		JLabel lblCourseName = new JLabel("班级名称", JLabel.RIGHT);
		lblCourseName.setBounds(fillWidth, fillHeight * 2 + compHeight, compWidth, compHeight);

		JLabel lblCredit = new JLabel("开班时间", JLabel.RIGHT);
		lblCredit.setBounds(fillWidth, fillHeight * 3 + compHeight * 2, compWidth, compHeight);

		JLabel lblHours = new JLabel("结束时间", JLabel.RIGHT);
		lblHours.setBounds(fillWidth, fillHeight * 4 + compHeight * 3, compWidth, compHeight);

		JLabel lblDescription = new JLabel("班级教师", JLabel.RIGHT);
		lblDescription.setBounds(fillWidth, fillHeight * 5 + compHeight * 4, compWidth, compHeight);

		txtClassNumber = new JTextField();
		txtClassNumber.setBounds(fillWidth * 2 + compWidth, fillHeight, compWidth * 2, compHeight);
		
		txtClassName = new JTextField();
		txtClassName.setBounds(fillWidth * 2 + compWidth, fillHeight * 2 + compHeight, compWidth * 2, compHeight);
		txtBeginDate = new JTextField();
		txtBeginDate.setBounds(fillWidth * 2 + compWidth, fillHeight * 3 + compHeight * 2, compWidth * 2, compHeight);
		
		
		txtEndDate = new JTextField();
		txtEndDate.setBounds(fillWidth * 2 + compWidth, fillHeight * 4 + compHeight * 3, compWidth * 2, compHeight);
				
		txt = new JTextField();
		txt.setBounds(fillWidth * 2 + compWidth, fillHeight * 5+ compHeight * 4, compWidth * 2, compHeight);
		
		List<ClazzBean> clazz = new ClazzManager().display();
		Object [] objs = new Object[clazz.size() + 1];
		objs[0] = "-请选择-";
		for (int i = 1; i < objs.length; i++) 
			objs[i] = clazz.get(i - 1).getClazzNumber() + ":" + clazz.get(i - 1).getTeacherNumber();
		
		cmbClazz = new JComboBox(objs);
		cmbClazz.setBounds(fillWidth * 2 + compWidth, fillHeight * 6 + compHeight * 7, compWidth * 2, compHeight);
		cmbClazz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pnlMain, 
						cmbClazz.getSelectedItem().toString(), "标题", JOptionPane.INFORMATION_MESSAGE );
			}
		});
		
		JButton btnAdd = new JButton("添加");
		btnAdd.setBounds(44, fillHeight * 7 + compHeight * 8, compWidth, compHeight);
		
		
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtClassNumber.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"班级编号不能为空", "标题", JOptionPane.INFORMATION_MESSAGE );
					txtClassNumber.requestFocus();
				}
				else if (txtClassName.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"班级名称不能为空", "标题", JOptionPane.INFORMATION_MESSAGE );
					txtClassName.requestFocus();
				}
				else if (txtBeginDate.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"开始时间不能为空", "标题", JOptionPane.INFORMATION_MESSAGE );
					txtBeginDate.requestFocus();
				}
				else if (txtEndDate.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"结束时间不能为空", "标题", JOptionPane.INFORMATION_MESSAGE );
					txtEndDate.requestFocus();
				}
				else if (txt.getText().length() == 0) {
					JOptionPane.showMessageDialog(pnlMain, 
							"教师编号不能为空", "标题", JOptionPane.INFORMATION_MESSAGE );
					txt.requestFocus();
				}
				// ... 非空验证
				else {
					ClazzBean classBean = new ClazzBean();
					classBean.setClazzNumber(txtClassNumber.getText());
					classBean.setClazzName(txtClassName.getText());
					classBean.setBeginDate(Date.valueOf(txtBeginDate.getText()));
					classBean.setBeginDate(Date.valueOf(txtBeginDate.getText()));
					classBean.setTeacherNumber(txt.getText());

					try {
						if (new ClazzManager().add(classBean)) { 	// 不能判断 == 1，考虑insert触发器问题
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
				txtClassNumber.setText("");
				txtClassName.setText("");
				txtBeginDate.setText("");
				txtEndDate.setText("");
				txt.setText("");
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
		
		pnlLeft.add(txtClassNumber);
		pnlLeft.add(txtClassName);
		pnlLeft.add(txtBeginDate);
		pnlLeft.add(txtEndDate);
		pnlLeft.add(txt);
		
		pnlLeft.add(cmbClazz);
		pnlLeft.add(btnAdd);
		pnlLeft.add(btnCancel);
		
		pnlCenter = new JPanel();
		splPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,pnlLeft,pnlCenter);
		splPanel.setDividerLocation(0.3);
	     /*
	      * 设置JSplitPane是否可以展开或收起(如同文件总管一般)，设为true表示打开此功能。
	      */
		splPanel.setOneTouchExpandable(true);
		splPanel.setDividerSize(10);//设置分隔线宽度的大小，以pixel为计算单位。
		
		showData();
		this.setContentPane(splPanel);
		this.setVisible(true);	// 显示
	}

	private void showData() {
		// 初始化Model信息
		List<ClazzBean> clazz = new ClazzManager().display();
		Object [][] datas = new Object[clazz.size()][5];
		String [] colName = {"班级编号", "班级名称", "开班时间", "结束时间", "班级教师"};
		
		for (int i = 0; i < clazz.size(); i++) {
			datas[i][0] = clazz.get(i).getClazzNumber();
			datas[i][1] = clazz.get(i).getClazzName();
			datas[i][2] = clazz.get(i).getBeginDate();
			datas[i][3] = clazz.get(i).getEndDate();
			datas[i][4] = clazz.get(i).getTeacherNumber();
		}
		// 以Model为参数创建View
		tabMain = new JTable(datas, colName);
		
	
		scrollPane.getViewport().add(tabMain); // JTable不能直接add()到Panel中
		pnlCenter.add(scrollPane);
	}


	

}
