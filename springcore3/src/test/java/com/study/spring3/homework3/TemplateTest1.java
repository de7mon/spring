package com.study.spring3.homework3;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring3.homework3.template.EmpDao;



public class TemplateTest1 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao", EmpDao.class);
		List<String> nowTime;
		System.out.println(empDao.queryAll());
		// 如何取得 eid=2 的員工姓名 ? (請使用 java 8 stream)
		//List<Map<String, Object>> emps = empDao.queryAll();
//		String ename = emps.stream()
//				.filter( e -> (e.get("eid")+"").equals("2"))
//				.findFirst()
//				.get()
//				.get("ename") + "";
		//System.out.println(ename);
		//System.out.println(empDao.queryAll());
		//empDao.deletTimelog();
		
		/*
		Homework		
		在每次的 查詢中 都可以將 查詢時間的Log記錄下來
		+-------------+--------------------+
		| method_name | log_timestamp      |
		+-------------+--------------------+
		| queryAll    | 2022/1/10 13:50:43 |
		+-------------+--------------------+
		| queryAll    | 2022/1/10 13:40:13 |
		+-------------+--------------------+
		| queryAll    | 2022/1/10 13:30:22 |	
		+-------------+--------------------+					
		*/
		List<Map<String, Object>> emps2 = empDao.queryTimelog();
		System.out.println(emps2);
		
		System.out.println("+---------------+-----------------------+");
		System.out.println("|  method_name  |     log_timestamp     |");
	
		for(Map m:emps2) {
			System.out.println("+---------------+-----------------------+");
			System.out.println("|  "+m.get("method_name")+"     |  "+m.get("log_timestamp")+"  |");
			
		}
		System.out.println("+---------------+-----------------------+");
		
		
	}

}
