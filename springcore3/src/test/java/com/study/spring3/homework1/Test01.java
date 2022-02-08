package com.study.spring3.homework1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.Attributes.Name;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
 public static void main(String[] args) {
	 ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext5.xml");
  
//  Student john=ctx.getBean("s1",Student.class);	 
//  System.out.println(john);	 
//
//  
//  Student mary=ctx.getBean("s2",Student.class);	 
//  System.out.println(mary);	 
//	
//  
// //請問mary的老師是誰? 印出name
//  System.out.println(mary.getName()+"的課程"+mary.getClazzs());
//  
//  
//  
//   Teacher[] teachers= {ctx.getBean("t1",Teacher.class),ctx.getBean("t2",Teacher.class)};
//   Set<Teacher> teachers2 =new LinkedHashSet<>();
//   
//   
//  for(Teacher teacher :teachers) {
//	  clazz_loop:
//	 for( Clazz cla1:teacher.getClazzs()) {
//		 for( Clazz cla2:mary.getClazzs()) {
//			 if(cla1.getId()==cla2.getId()) {
//				 //System.out.println(teacher.getName());
//				 teachers2.add(teacher);
//				 break clazz_loop ;
//			 }
//		 }
//	 }	
//	  System.out.println(mary.getName()+"的老師"+teachers2);
//	  System.out.println(mary.getName()+"的老師"+
//	  teachers2.stream().map(Teacher::getName).collect(Collectors.toSet()));
//	  
//	  
// }
//
////請問John的老師是誰? 印出name
// for(Teacher teacher :teachers) {
//	 clazz_loop:
//	 for( Clazz cla1:teacher.getClazzs()) {
//		 for( Clazz cla2:john.getClazzs()) {
//			 if(cla1.getId()==cla2.getId()) {
//				 System.out.println(teacher.getName());
//				 break clazz_loop;
//			 }
//		 }
//	 }		 
// }
//
//
	 
	 
	 //JAVA8 作業
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


