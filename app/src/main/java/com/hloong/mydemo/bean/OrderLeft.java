package com.hloong.mydemo.bean;

public class OrderLeft{
	 
	public String airline;
	public String from;
	public String to;
	
	public OrderLeft(String airline,String from,String to) {
	    super();
	    this.airline = airline;
	    this.from = from;
	    this.to = to;
	}
	
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}
