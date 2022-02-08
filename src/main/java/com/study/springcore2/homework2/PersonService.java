package com.study.springcore2.homework2;

import java.util.Date;
import java.util.List;

public interface PersonService {
  boolean append(String name,Date birth);
  boolean append(Person person);
  List<Person> findAllPersons();
  Person getPerson(String name);
  List<Person> getPerson(Date birth);  
  void updateBirth(String name, Date birth);
  void deletPerson(Person person);
  List<Person> getAgePerson(int age);
}
