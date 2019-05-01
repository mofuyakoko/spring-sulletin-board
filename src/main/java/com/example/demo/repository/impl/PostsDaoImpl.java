package com.example.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Posts;
import com.example.demo.repository.PostsDao;

@Repository
public class PostsDaoImpl implements PostsDao {

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int count() throws DataAccessException {
		return 0;
	}

	@Override
	public int insertOne(Posts posts) throws DataAccessException {
		return 0;
	}

	@Override
	public Posts selectOne(String post_id) throws DataAccessException {
		return null;
	}

	@Override
	public Posts selectMany() throws DataAccessException {
		return null;
	}

	@Override
	public int updateOne(Posts posts) throws DataAccessException {
		return 0;
	}

	@Override
	public int deleteOne(String post_id) throws DataAccessException {
		return 0;
	}

	@Override
	public void postsCsvOut() throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
