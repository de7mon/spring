package com.study.springcore2.homework3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.springcore2.homework3.template.EmpJobDao;



public class TemplateTest3 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpJobDao empJobDao = ctx.getBean("empJobDao", EmpJobDao.class);
		
		//empJobDao.queryEmps().forEach(System.out::println);
		//empJobDao.queryJobs().forEach(System.out::println);
		//empJobDao.queryEmps2().forEach(System.out::println);
		empJobDao.queryJobs().forEach(System.out::println);
			
			
			
			
			
			
         
	}

}
