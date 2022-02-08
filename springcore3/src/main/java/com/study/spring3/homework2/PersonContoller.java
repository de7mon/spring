package com.study.spring3.homework2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/*
 * 功能:
 * 1.建立 Person 資料
 *     ->輸入 name ,birth
 * 2.取得 Person 全部資料
 *     ->不用輸入參數
 * 3.根據姓名取得 Person 的生日
 *     ->輸入 name 
 * 4.取得今天生日的 Person
 *     ->輸入今天的日期
 * 5.取得某一歲數以上的 Person
 *     ->輸入 age 
 * 6.根據姓名來修改Person的生日
 *     ->輸入 name ,birth
 * 7.根據姓名來刪除Person
 *     ->輸入 name
 * */


@Controller
public class PersonContoller {
	@Autowired
    private PersonService personService;
	
	public void addPerson(String name ,int yyyy,int MM,int dd) {
		//1.判斷 name ,yyyy,MM,dd 是否有資料
		//2.將 yyyy/MM/dd轉日期格式
	    SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd");
        try {
			Date birth=sdf.parse(yyyy+"/"+MM+"/"+dd);
			addPerson(name, birth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	public void addPerson(String name ,Date birth) {
		//1.判斷 name 與 birth 是否有資料
		//2.建立Person 資料
	
		boolean check=personService.append(name,birth);
		System.out.println("add person"+check);
	}
	public void printAllPersons() {
		//System.out.println(personService.findAllPersons());
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		//資料呈現
		List<Person> people= personService.findAllPersons();
		System.out.println("+--------------+---------+--------------+ ");
		System.out.println("| name         |     age |     birthday | ");//12,7,12
		System.out.println("+--------------+---------+--------------+");
		for(Person p:people) {
			String birthday =sdf.format(p.getBirth());
			System.out.printf("| %-12s | %7d | %12s |\n",p.getName(),p.getAge(),birthday);
			System.out.println("+--------------+---------+--------------+"); 	
		}
	}
	//根據姓名取得 Person
	public Person getPersonByName(String name) {				
		Person person=personService.getPerson(name);
		return person;
	}
	

	
	//今天生日的 Person
		public List<Person> getPersonByTodayBirth(int MM,int dd) {
			SimpleDateFormat sdf =new SimpleDateFormat("MM/dd");
	        try {
				Date birth=sdf.parse(MM+"/"+dd);				
				List<Person> person=personService.getPerson(birth);
				return person;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}								
		}
		//取得某一歲數以上的 Person
		public List<Person> getPersonByAge(int age){
			
			
			
			return personService.getAgePerson(age);
		}
		
        //根據姓名來修改Person的生日 
		public void updatePersonBrithByName(String name) {
			
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd");
			if (!personService.findAllPersons().equals(personService.getPerson(name))) {
				try {
					System.out.println("請輸入 生日 年 月 日:");
					Scanner sc=new Scanner(System.in);		
					int yyyy=sc.nextInt();
					int MM=sc.nextInt();
					int dd=sc.nextInt();	
					Date birth=sdf.parse(yyyy+"/"+MM+"/"+dd);
					personService.updateBirth(name, birth);
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}		
		}
		//根據姓名來刪除Person
		public void deletPersonByName(String name) {
			if(getPersonByName(name)!=null){
				Person person=getPersonByName(name);
				personService.deletPerson(person);
			}else {
				System.out.println("刪除失敗");
			}
			
			
		}
		
	
}
