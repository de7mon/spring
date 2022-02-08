package com.study.springcore2.homework3;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.springcore2.homework3.template.EmpDao;



public class TemplateTest1 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao", EmpDao.class);
		List<String> nowTime;
		System.out.println(empDao.queryAll());
		
		List<Map<String, Object>> emps2 = empDao.queryTimelog();
		System.out.println(emps2);
		
		System.out.println("+---------------+-----------------------+");
		System.out.println("|  method_name  |     log_timestamp     |");
	
		for(Map m:emps2) {
			System.out.println("+---------------+-----------------------+");
			System.out.println("|  "+m.get("method_name")+"     |  "+m.get("log_timestamp")+"  |");
			
		}
		System.out.println("+---------------+-----------------------+");
		
				
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

		
		
		
	}

}
