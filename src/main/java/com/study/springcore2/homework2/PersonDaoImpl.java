package com.study.springcore2.homework2;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao{
        @Autowired
    	private JsonDB jsonDB;

		@Override
		public boolean create(Person person) {
			Boolean check=null; 
			try {
				check=jsonDB.add(person);
			} catch (Exception e) {
                e.printStackTrace();
			    check=false;			
			}
			return check;
		}

		@Override
		public List<Person> readAll() {
			List<Person> people=null;
			try {
				people=jsonDB.queryAll();
			} catch (Exception e) {
				e.printStackTrace();
				people=null;
			}		
			return people;
		}

		@Override
		public void update(String name ,Date birth) {
			try {
				jsonDB.updateBirth(name, birth);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
		}

		@Override
		public void delet(Person person) {			
			try {
				jsonDB.delet(person);
			} catch (Exception e) {
                e.printStackTrace();
			   			
			}
			
		}

		}

	

