package com.hust.studentmis.xin.ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hust.studentmis.xin.business.CourseManager;
import com.hust.studentmis.xin.entity.CourseBean;

public class ShowDemoFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private CourseManager manager = new CourseManager();

	private JScrollPane pnlMain;
	private  JTable tabMain ;
	private  String[]  colName = {
			"课程编号","课程名称","学分","课时","课程描述"
	};


	public ShowDemoFrame() {
		this.init();
	}


	private void init(){
		this.setSize(new Dimension(340, 450));
		this.setLocationRelativeTo(null);  //居中
		this.setResizable(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	

		List<CourseBean> courses =  manager.display();
		Object [][]   datas = new Object[courses.size()][5];
		for(int i=0; i<courses.size(); i++) {
			datas[i][0] = courses.get(i).getCourseNumber();
			datas[i][1] = courses.get(i).getCourseName();
			datas[i][2] = courses.get(i).getCredit();
			datas[i][3] = courses.get(i).getHours();
			datas[i][4] = courses.get(i).getDescription();

		}
		
		pnlMain =new JScrollPane();
		tabMain = new JTable(datas,colName);
		
		pnlMain.getViewport().add(tabMain); //JTable不能直接添加
		this.add(pnlMain);
		this.setVisible(true);
	}




}
