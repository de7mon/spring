package com.study.springcore2.homework2;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonDao {

	public static void main(String[] args) throws Exception {
      ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext2.xml");
	  PersonDao personDao=ctx.getBean("personDaoImpl",PersonDaoImpl.class);

	  
	 SimpleDateFormat sdf=new SimpleDateFormat("MM/dd");
	 sdf.parse("1/1");
	 System.out.println(sdf.parse("1/1"));
	}

}
