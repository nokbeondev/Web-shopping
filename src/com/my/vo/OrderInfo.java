package com.my.vo;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private int order_no;
	//private String order_id;
	private Customer customer;
	private Date order_date;
	private List<OrderLine> order_lines;
	public OrderInfo() {
		super();
	}
	public OrderInfo(int order_no, Customer customer, Date order_date) {
		super();
		this.order_no = order_no;
		this.customer = customer;
		this.order_date = order_date;
	}	
	
	public OrderInfo(int order_no, Customer customer, Date order_date, List<OrderLine> order_lines) {
		super();
		this.order_no = order_no;
		this.customer = customer;
		this.order_date = order_date;
		this.order_lines = order_lines;
	}
	@Override
	public String toString() {
		return "OrderInfo [order_no=" + order_no + ", customer=" + customer + ", order_date=" + order_date
				+ ", order_lines=" + order_lines + "]";
	}	
	public List<OrderLine> getOrder_lines() {
		return order_lines;
	}
	public void setOrder_lines(List<OrderLine> order_lines) {
		this.order_lines = order_lines;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	
}
