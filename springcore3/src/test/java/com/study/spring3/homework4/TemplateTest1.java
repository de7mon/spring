package com.study.spring3.homework4;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring3.homework4.template.ItemServiceImpl;


public class TemplateTest1 {
	private boolean stop;
	ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc2-config.xml");
	ItemServiceImpl itSv = ctx.getBean("itemServiceImpl", ItemServiceImpl.class);
	private void select() {		
		
		System.out.println("====================================");
		System.out.println("1.每一張發票有那些商品");
		System.out.println("2.每一張發票有幾件商品");
		System.out.println("3.每一張發票價值多少");
		System.out.println("4.每一樣商品各賣了多少");
		System.out.println("5.哪一件商品賣得錢最多");
		System.out.println("6.哪一張發票價值最高");
		System.out.println("0.離開");
		System.out.println("=====================================");
		System.out.println("請選擇");		
		Scanner sc=new Scanner(System.in);
		int s =sc.nextInt();
		switch (s) {
		  case 1: 
			  itSv.InvoiceProduct();
		    	break;
		    case 2: 
		    	itSv.InvoiceProductCount();
		    	break;
		    case 3:
		    	itSv.InvoiceMoney();
		    	
		    	break;
		    case 4:
		    	itSv.ProductMoney();
		    	break;
		    case 5:
		    	itSv.ProductMaxMoney();
		    	break;
		    case 6: 
		    	itSv.InvoiceMaxMoney();
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
   private void start() {
		while(!stop) {
			select();
		}
	}
	public static void main(String[] args) {
		
		
			
		new TemplateTest1().start();
		
	}

}
