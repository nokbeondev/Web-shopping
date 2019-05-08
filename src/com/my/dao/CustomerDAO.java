package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;

public interface CustomerDAO {
	void insert(Customer c) throws AddException;
	Customer selectById(String id) throws FindException;
	List<Customer> selectByName(String name);
}


