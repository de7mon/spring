package com.study.springcore2.homework2;

import java.util.Date;
import java.util.List;

public interface PersonDao {
	//建立 Person
  public boolean create(Person person); 
  List<Person> readAll();
  void update(String name, Date birth);
  void delet(Person person);
}
