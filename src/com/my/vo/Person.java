package com.my.vo;

import java.io.Serializable;

public class Person implements Serializable{
	protected String name;
	protected String zipcode;
	protected String address;
	public Person() {		
	}
	public Person(String name) {
		this.name = name;
		//this(name, null, null);
	}
	public Person(String name, String zipcode, String address) {
		this.name = name;
		this.zipcode = zipcode;
		this.address = address;
	}
	@Override
	public String toString() {
		return "name:" + name +", zipcode:" + zipcode +", address:" + address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
