package com.study.springcore2.homework4.entity;

import java.util.Date;
import java.util.List;

public class Invoice {

	private Integer id;
	private Date invdate;
	

	private List<Item> items;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getInvdate() {
		return invdate;
	}
	public void setInvdate(Date invdate) {
		this.invdate = invdate;
	}
			
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invdate=" + invdate +  ", items=" +
	items
				+ "]";
	}
	
	
	
	
	
	
}
