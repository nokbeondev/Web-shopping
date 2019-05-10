package com.my.dao;

import java.util.List;

import com.my.exception.FindException;
import com.my.vo.Post;

public interface PostDAO {
	void insert(Post post);
	List<Post> selectByDoro(String doro) throws FindException;
}
