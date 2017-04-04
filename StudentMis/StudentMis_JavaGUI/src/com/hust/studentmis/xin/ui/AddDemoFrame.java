package com.hust.studentmis.xin.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.hust.studentmis.xin.business.ClazzManager;
import com.hust.studentmis.xin.business.StudentManager;
import com.hust.studentmis.xin.entity.ClazzBean;
import com.hust.studentmis.xin.entity.StudentBean;

public class AddDemoFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private StudentManager manager = new StudentManager();
	private StudentBean student;
	private String message;

	private JPanel pnlMain;
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

	private JButton btnAdd ;
	private JButton btnCancel;
	private JRadioButton btnMan;
	private JRadioButton btnWoman;
	private ButtonGroup group;

	public AddDemoFrame() {
		this.init();
	}

	private void init(){
		this.setSize(new Dimension(340, 450));
		this.setLocationRelativeTo(null);  //居中
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//创建控件
		pnlMain =new JPanel();
		this.setContentPane(pnlMain);
		pnlMain.setLayout(null);
		this.lbl1 = new JLabel("学生编号：");
		this.lbl2 = new JLabel("学生名称：");
		this.lbl3 = new JLabel("学生性别：");
		this.lbl4 = new JLabel("学生生日：");
		this.lbl5 = new JLabel("学生联系方式：");
		this.lbl6 = new JLabel("学生地址：");
		this.lbl7 = new JLabel("学生班级：");
		this.txt1 = new JTextField();
		this.txt2 = new JTextField();
		this.txt3 = new JTextField();
		this.txt4 = new JTextField();
		this.txt5 = new JTextField();
		this.txt6  = new JTextField() ;  
		this.btnAdd = new JButton("添加");
		this.btnCancel = new JButton("取消");
		
		btnMan = new JRadioButton("男", true);
		btnWoman = new JRadioButton("女");
		group = new ButtonGroup();
		group.add(btnMan);
		group.add(btnWoman);

		QuoteListener listener = new QuoteListener();
		btnMan.addActionListener(listener);
		btnWoman.addActionListener(listener);
		

		//设置控件位置
		int btnWidth = 20;
		int btnHeight = 20;
		int lblSpaceY = 5;    //垂直间隔
		int lblStartX = 45;
		int lblWidth = 100;
		int txtStartX = lblStartX + lblWidth + 10;
		int txtWidth = 150;
		int lblStartY = 15;
		int lblHeight = 40;
		int txtStartY = lblStartY + 10;
		int txtHeight = 25;
		int i = 1;


		List<ClazzBean> clazzes = new ClazzManager().display();
		Object[] objs = new Object[clazzes.size() + 1];
		objs[0] = "-请选择-";
		for (int j = 1; j < objs.length; j++) {
			objs[j] = clazzes.get(j - 1).getClazzNumber() + ":" + clazzes.get(j - 1).getClazzName();
		}
		cmb1 = new JComboBox(objs);

		lbl1.setBounds(lblStartX, lblStartY, lblWidth, lblHeight);
		txt1.setBounds(txtStartX, txtStartY, txtWidth, txtHeight); 
		lbl2.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		txt2.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i++, txtWidth, txtHeight);
		
		lbl3.setBounds(lblStartX, lblStartY + (lblHeight + lblSpaceY) * i, lblWidth, lblHeight);
		this.btnMan.setBounds(txtStartX, txtStartY + (lblHeight + lblSpaceY) * i, btnWidth, btnHeight);
		this.btnWoman.setBounds(txtStartX + 30, txtStartY + (lblHeight + lblSpaceY) * i++, btnWidth, btnHeight);
		
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

		cmb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pnlMain, 
						cmb1.getSelectedItem().toString(), "标题", JOptionPane.INFORMATION_MESSAGE );
			}
		}); 


		/*		//限制只能输入数字
		txt4.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();				
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

				}else{
					e.consume(); //关键，屏蔽掉非法输入
				}
			}
		});*/



        //验证
		this.btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txt1.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "学生编号不能为空", "提示消息",   JOptionPane.INFORMATION_MESSAGE);
					txt1.requestFocus();
				} else if(txt2.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "学生姓名不能为空", "提示消息",   JOptionPane.INFORMATION_MESSAGE);
					txt2.requestFocus();
				} else  if(txt3.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "学生性别不能为空", "提示消息",   JOptionPane.INFORMATION_MESSAGE);
					txt3.requestFocus();
				} else  if(txt4.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "学生生日不能为空", "提示消息",   JOptionPane.INFORMATION_MESSAGE);
					txt4.requestFocus();
				} else  if(txt5.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "学生联系方式不能为空", "提示消息",   JOptionPane.INFORMATION_MESSAGE);
					txt5.requestFocus();
				} else  if(txt6.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog( pnlMain, "学生地址不能为空", "提示消息",   JOptionPane.INFORMATION_MESSAGE);
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
						message = "添加成功";
						clearTextContent();
					} else {
						message = "添加失败";
					}
					JOptionPane.showMessageDialog( pnlMain,  message , "提示消息",   JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});


		this.btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearTextContent();
			}

		});


		pnlMain.add(lbl1); 
		pnlMain.add(txt1); 
		pnlMain.add(lbl2);
		pnlMain.add(txt2); 
		pnlMain.add(lbl3);
		pnlMain.add(btnMan);
		pnlMain.add(btnWoman);
		pnlMain.add(lbl4);	
		pnlMain.add(txt4); 
		pnlMain.add(lbl5);
		pnlMain.add(txt5); 
		pnlMain.add(lbl6);	
		pnlMain.add(txt6); 
		pnlMain.add(lbl7);	
		pnlMain.add(cmb1);

		pnlMain.add(btnAdd);     
		pnlMain.add(btnCancel );
		
		this.setVisible(true);

	}


	private class QuoteListener implements ActionListener {
		//根据按下的单选按钮设置标签的内容
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if(source == btnMan)
				btnMan.setText("男");
			else if(source == btnWoman)
				btnWoman.setText("女");

		}
	}


	public void clearTextContent() {
		txt1.setText("");
		txt2.setText("");
		txt3.setText("");
		txt4.setText("");
		txt5.setText("");
		txt6.setText("");
	}

}
