package com.example.demo.repository;

import org.springframework.dao.DataAccessException;

import com.example.demo.domain.model.Posts;

public interface PostsDao {

	public int count() throws DataAccessException;
	
	public int insertOne(Posts posts) throws DataAccessException;
	
	public Posts selectOne(String postId) throws DataAccessException;
	
	public Posts selectMany() throws DataAccessException;
	
	public int updateOne(Posts posts) throws DataAccessException;
	
	public int deleteOne(String postId) throws DataAccessException;
	
	public void postsCsvOut() throws DataAccessException;
}
