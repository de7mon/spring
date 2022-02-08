package com.study.spring3.homework2;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonDao {

	public static void main(String[] args) throws Exception {
      ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext2.xml");
	  PersonDao personDao=ctx.getBean("personDaoImpl",PersonDaoImpl.class);
	  //System.out.println(personDao.readAll());
	  
	  
	  
//	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
//	 boolean check= personDao.create(new Person("mary",0,sdf.parse("2000/1/1")));
//	 System.out.println(check);
//	 System.out.println(personDao.readAll());
	 SimpleDateFormat sdf=new SimpleDateFormat("MM/dd");
	 sdf.parse("1/1");
	 System.out.println(sdf.parse("1/1"));
	}

}
