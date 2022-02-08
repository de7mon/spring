package com.study.spring3.homework2;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJsonDB {

	public static void main(String[] args) throws Exception {
      ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext2.xml");
	  JsonDB jsonDB=ctx.getBean("jsonDB",JsonDB.class);
	  System.out.println(jsonDB.queryAll());
	  
	   
	  
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
	 boolean check= jsonDB.add(new Person("Joh1",0,sdf.parse("2000/1/1")));
	 System.out.println(jsonDB.queryAll());
	
	}

}
