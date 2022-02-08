package com.study.springcore2.homework2;


import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class JsonDB {//Json 資料庫
	
    @Autowired
	private Gson gson; 
	//Json file  資料庫存放地
	private static final Path PATH=Paths.get("src/main/java/com/study/springcore2/homework2/person.json");
	
	//查詢全部
	public List<Person> queryAll() throws Exception{
	  String jsonStr= Files.readAllLines(PATH).stream().collect(Collectors.joining());
		Type type=new TypeToken<ArrayList<Person>>() {			
		}.getType();
		List<Person> people=gson.fromJson(jsonStr, type);

		Date today =new Date();
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(today);
		int todayYear=calendar.get(Calendar.YEAR);
		for(Person person:people) {
			calendar.setTime(person.getBirth());
		    int birthYear =calendar.get(Calendar.YEAR);
		    int age=todayYear-birthYear;
		    person.setAge(age);	
		}	
		return people;
	}
	public boolean add(Person person) throws Exception {
		List<Person> people=queryAll();
		Date today =new Date();
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(today);
		int todayYear=calendar.get(Calendar.YEAR);		
			calendar.setTime(person.getBirth());
		    int birthYear =calendar.get(Calendar.YEAR);
		    int age=todayYear-birthYear;
		    person.setAge(age); 
		//作業 :如果person已存在則return false
		// name,age,birth 皆與目前資料庫的person資料相同
				
		   boolean check=false;			
			for(Person p:people) {
				if(p.equals(person)) {
					check=true;
					System.out.println("資料重複");
					break;
				}					
				}		
	        if(check==false) {
	        	people.add(person);
				String newJsonString =gson.toJson(people);
				Files.write(PATH, newJsonString.getBytes("UTF-8"));
				System.out.println("資料加入成功");
	        }
			System.out.println(check);
			return check;  
	}
	public void updateBirth(String name ,Date birth) throws Exception {
		
		List<Person> people=queryAll();	
	Person person=queryAll().stream().
				filter(p->(p.getName().equals(name))).findFirst().get();
	     for(Person p:people) {
			 int a=0;			
			 if (p.getName().equals(person.getName())) {
				p.setBirth(birth);
				people.set(a, person);
				 String newJsonString =gson.toJson(people);
					Files.write(PATH, newJsonString.getBytes("UTF-8"));
					System.out.println("更改成功");
					break;
			}
			 a++;
			}
			
		 
		   
	}
	public void delet(Person person) throws Exception{
		List<Person> people=queryAll();		    
		for(Person p:people) {	
			if(p.getName().equals(person.getName())) {				
				people.remove(person);
				String newJsonString =gson.toJson(people);
			    Files.write(PATH, newJsonString.getBytes("UTF-8"));
				System.out.println("刪除成功");				
				break;
			}
			
		}
		
		
	}
	
	
	
}
