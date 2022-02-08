package com.study.springcore2.homework2;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonSystem {
	private ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext2.xml");
	private PersonContoller personContoller =ctx.getBean("personContoller",PersonContoller.class);
    private boolean stop;
	private void menu() {
		System.out.println("====================================");
		System.out.println("1.建立 Person 資料");
		System.out.println("2.取得 Person 全部資料");
		//作業 2:
		System.out.println("3.根據姓名取得 Person 的生日");
		System.out.println("4.取得今天生日的 Person");
		System.out.println("5.取得某一歲數以上的 Person");
		System.out.println("6.根據姓名來修改Person的生日");
		System.out.println("7.根據姓名來刪除Person");
		System.out.println("0.離開");
		System.out.println("=====================================");
		System.out.println("請選擇");		
		Scanner sc=new Scanner(System.in);
		int choice=sc.nextInt();
	    switch(choice) {
	    case 1: 
	    	createPerson();
	    	break;
	    case 2: 
	    	printPerson();
	    	break;
	    case 3:
	    	namePerson();
	    	
	    	break;
	    case 4:
	    	birthPerson();
	    	break;
	    case 5:
	    	agePerson();
	    	break;
	    case 6: 
	    	updatebirth(); 
	    	break;
	    case 7:
	    	deletPerson();
	    	break;
	    case 0:
	    	System.out.println("離開系統");
	    	stop =true;
	    	break;
	   default :
		   System.out.println("輸入錯誤"); 
		   break;
	    }
	    	
	
	}
	private void createPerson() {
		System.out.println("請輸入姓名 生日年 月 日:");
		//Ex: Jack 1999 4 5
		Scanner sc=new Scanner(System.in);
		String name= sc.next();
		int yyyy=sc.nextInt();
		int MM=sc.nextInt();
		int dd=sc.nextInt();
		personContoller.addPerson(name,yyyy,MM,dd);
	}
	private void printPerson() {
		personContoller.printAllPersons();
	}
	private void namePerson() {
		System.out.println("請輸入姓名:");
		Scanner sc=new Scanner(System.in);
		String name= sc.next();	
		if(personContoller.getPersonByName(name)==null) {
			System.out.println("姓名輸入錯誤");
		}else {
			System.out.println(personContoller.getPersonByName(name).getBirth());
		}
		
		
		 
	}
	private void birthPerson() {
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd");		
		System.out.println("請輸入 生日 月 日:");
		Scanner sc=new Scanner(System.in);		
		int MM=sc.nextInt();
		int dd=sc.nextInt();	
	System.out.println(personContoller.getPersonByTodayBirth(MM,dd));		
	}
	
	
	private void updatebirth() {
		System.out.println("請輸入姓名:");
		Scanner sc=new Scanner(System.in);
		String name= sc.next(); 		
		if(personContoller.getPersonByName(name)==null) {
			System.out.println("姓名輸入錯誤");
		}else {
			personContoller.updatePersonBrithByName(name);
		}	
	}
	private void agePerson() {
		System.out.println("請輸入年齡:");
		Scanner sc=new Scanner(System.in);
		int age= sc.nextInt();
		System.out.println("年齡"+age+"以上的有");
		System.out.println(personContoller.getPersonByAge(age));
	}
	
	
	private void deletPerson() {
		System.out.println("請輸入姓名:");
		Scanner sc=new Scanner(System.in);
		String name= sc.next(); 
		personContoller.deletPersonByName(name);
	}
	public void start() {
		while(!stop){
			menu();
		}
     
    }

	public static void main(String[] args) {
		new PersonSystem().start();

	}
}
