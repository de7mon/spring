package com.study.springcore2.homework2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonContoller {

	public static void main(String[] args) throws Exception{
ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext2.xml");
		PersonContoller personContoller =ctx.getBean("personContoller",PersonContoller.class);
	
		System.out.println(personContoller.getPersonByName("Randy"));
		System.out.println(personContoller.getPersonByName("Tom"));
		
	}

}
