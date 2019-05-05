package com.example.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.User;
import com.example.demo.repository.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao dao;

	/**
	 * 新規ユーザーを一件登録する
	 * @param user
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public boolean insertNewUser(User user) throws DataAccessException, IOException {
		int insertCnt = dao.insertOne(user);
		boolean result = false;
		if (insertCnt > 0) {
			result = true;
		}
		return result;
	}
	
	/**
	 * ユーザーIDに紐づくユーザーが存在しているが確認する。
	 * @param userId
	 * @return 
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public boolean countOneUser(User user) throws DataAccessException, IOException{
		int count = dao.countOne(user.getUserId());
		boolean result = false;
		if(count > 0) {
			result = true;
		}
		return result;
	}
}
