package com.study.springcore2.homework4.entity;

import java.util.List;

public class Item {

	private Integer id;
	private Integer amount;
	private Integer ipid;
	private Integer invid;
	
	private List<ItemProduct> ItemProducts;
	private Invoice invoices;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getIpid() {
		return ipid;
	}
	public void setIpid(Integer ipid) {
		this.ipid = ipid;
	}
	public Integer getInvid() {
		return invid;
	}
	public void setInvid(Integer invid) {
		this.invid = invid;
	}
	public List<ItemProduct> getItemProducts() {
		return ItemProducts;
	}
	public void setItemProducts(List<ItemProduct> itemProducts) {
		ItemProducts = itemProducts;
	}
	public Invoice getInvoices() {
		return invoices;
	}
	public void setInvoices(Invoice invoices) {
		this.invoices = invoices;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", amount=" + amount + ", ipid=" + ipid + ", invid=" + invid + ", ItemProducts="
				+ ItemProducts + ", invoices=" + invoices + "]";
	}
	
	
	
	
	
	
	
	
	
}
