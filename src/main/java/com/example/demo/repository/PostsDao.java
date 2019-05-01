package com.example.demo.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.domain.model.Posts;

public interface PostsDao {

	public int count() throws DataAccessException,IOException;
	
	public int insertOne(Posts posts) throws DataAccessException,IOException;
	
	public Posts selectOne(String postId) throws DataAccessException,IOException;
	
	public List<Posts> selectMany() throws DataAccessException,IOException;
	
	public int updateOne(Posts posts) throws DataAccessException,IOException;
	
	public int deleteOne(String postId) throws DataAccessException,IOException;
	
	public void postsCsvOut() throws DataAccessException,IOException;
}
