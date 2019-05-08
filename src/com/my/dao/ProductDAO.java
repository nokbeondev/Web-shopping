package com.my.dao;

import java.util.List;

import com.my.exception.FindException;
import com.my.vo.Product;

public interface ProductDAO {
	public Product selectByNo(String s) throws FindException;
	public List<Product> selectByName(String s) throws FindException;
	public List<Product> selectAll() throws FindException;	
}
