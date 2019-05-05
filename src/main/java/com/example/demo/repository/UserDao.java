package com.example.demo.repository;

import java.io.IOException;

import org.springframework.dao.DataAccessException;

import com.example.demo.domain.model.Posts;
import com.example.demo.domain.model.User;

public interface UserDao {

	public int countAll() throws DataAccessException,IOException;
	
	public int countOne(String userId) throws DataAccessException,IOException;
	
	public int insertOne(User user) throws DataAccessException,IOException;
	
	public Posts selectOne(String userId) throws DataAccessException,IOException;
	
	public Posts selectMany() throws DataAccessException,IOException;
	
	public int updateOne(User user) throws DataAccessException,IOException;
	
	public int deleteOne(String userId) throws DataAccessException,IOException;
	
}
