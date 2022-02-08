package com.study.springcore2.homework3.template;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class aop {
	 static List<String> querryTime = new ArrayList<String>();
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	@Pointcut(value = "execution(* com.study.springcore2.homework3.template.EmpDao.queryAll())")
	public void pt() {

	}
	@AfterReturning(value = "pt()")
	public void afterReturning(JoinPoint joinPoint) {		
//	
//		 System.out.println(querryTime.toString());
		//System.out.println(joinPoint.getSignature().getName());
		String nowTime= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				 .format(Calendar.getInstance().getTime());	
		
//		
		jdbcTemplate.update("INSERT INTO timelog (method_name ,log_timestamp) VALUES (?, ?)",joinPoint.getSignature().getName(),nowTime);
		
		
		 

		 
       
	}
	
}
