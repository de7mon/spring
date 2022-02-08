package com.study.spring3.homework1;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.ls.LSOutput;



public class HomeWork {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext1.xml");
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
