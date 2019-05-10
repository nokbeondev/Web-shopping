package com.my.vo;

public class Customer extends Person{
	private String id;
	transient private String pwd;
	public Customer(String id, String pwd) {
		//super();
		this.id = id;
		this.pwd = pwd;
	}
	public Customer() {
		//super();		
	}
	public Customer(String id, String pwd, String name, Post post, String address) {
		super(name, post, address);
		this.id = id;
		this.pwd = pwd;
	}
		
	@Override
	public String toString() {
		return "id:" + id +", pwd:" + pwd + "," + super.toString();
	}
		
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Customer) {
			Customer c1 = (Customer)obj;	
			if(this.id == null || c1.id == null) {
				return false;
			}
			if(this.id.equals(c1.id)) {
				return true;
			}
		}
		return false;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	
}
