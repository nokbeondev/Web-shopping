package com.my.vo;

import java.io.Serializable;

public class Person implements Serializable{
	protected String name;
	protected Post post;
	protected String address;
	
	public Person() {		
	}
	
	public Person(String name) {
		this.name = name;
		//this(name, null, null);
	}
	
	public Person(String name, Post post, String address) {
		this.name = name;
		this.post = post;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", post=" + post + ", address=" + address + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	
}
