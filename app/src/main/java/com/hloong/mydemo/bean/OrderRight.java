package com.hloong.mydemo.bean;

public class OrderRight  {
	public String type;
	public String title;
	public String expireDate;
	public String price;
	
	public OrderRight(String type,String title,String expireDate,String price){
	    super();
	    this.type = type;
	    this.title = title;
	    this.expireDate = expireDate;
	    this.price = price;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
