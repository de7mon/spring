package com.study.spring3.homework2;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PersonServiceImpl implements PersonService{
    private static final String String = null;
	@Autowired
	private PersonDao personDao;
	@Override
	public boolean append(String name, Date birth) {
		Person person=new Person();
		person.setName(name);
		person.setBirth(birth);		
		
		return append(person);
	}
	@Override
	public boolean append(Person person) {
		return personDao.create(person);
	}
	@Override
	public List<Person> findAllPersons() {
		return personDao.readAll();
	}
	@Override
	public Person getPerson(String name) {
		
		Optional<Person> optPerson =findAllPersons()
				.stream()
				.filter(p->p.getName().equals(name)).findFirst();
		
		return optPerson.isPresent() ? optPerson.get() : null;
	}	
	@Override
	public List<Person> getPerson(Date birth) {
		List<Person> listPerson =findAllPersons()
				.stream()
				.filter(p->(p.getBirth().getDate()==birth.getDate())
						&& (p.getBirth().getMonth()==birth.getMonth())						
						).collect(Collectors.toList());		
		return listPerson.isEmpty()?null:listPerson;
	}
	@Override
	public void updateBirth(String name ,Date birth) {
		personDao.update(name ,birth);
		
	}
	@Override
	public List<Person> getAgePerson(int age) {
		List<Person> listPerson =findAllPersons()
				.stream()
				.filter(p->p.getAge()>=age).collect(Collectors.toList());		
		return listPerson.isEmpty()?null:listPerson;		
	}
	@Override
	public void deletPerson(Person person) {
		personDao.delet(person);
		
	}	
}
