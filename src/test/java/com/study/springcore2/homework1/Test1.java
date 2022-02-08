package com.study.springcore2.homework1;

import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test1 {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext1.xml");
		// 回家作業: 請問 mary 的老師有誰 ? 印出 name (請使用Java 8)		
		Student mary=ctx.getBean("s2",Student.class);	 	 
		Teacher t1= ctx.getBean("t1",Teacher.class);
		Teacher t2= ctx.getBean("t2",Teacher.class);
		
			Stream.of(t1,t2).filter(
					t->{
						for(Clazz c:mary.getClazzs()) {
							if(t.getClazzs().contains(c)) {
								return true;
							}
						}
						return false;
					}									
					).map(Teacher::getName).forEach(System.out::println);
		

	}

}
