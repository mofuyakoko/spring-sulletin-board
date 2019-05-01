package com.example.demo.repository.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Posts;
import com.example.demo.domain.model.User;
import com.example.demo.repository.UserDao;
import com.example.demo.util.DmlType;
import com.example.demo.util.FileIoUtil;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	FileIoUtil util;
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int count() throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int insertOne(User user) throws DataAccessException, IOException {
		String sql = util.readSqlFile("insertNewUser", DmlType.INSERT);
		System.out.println(sql);
		int result = jdbc.update(sql, user.getUserId(), user.getPassword(), user.getUserName(), user.getBirthday(),
				user.getCreateDate(), user.getUpdateDate());
		return result;
	}

	@Override
	public Posts selectOne(String userId) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Posts selectMany() throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int updateOne(User user) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int deleteOne(String userId) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
