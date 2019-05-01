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

	public void insertNewUser(User user) {
		try {
			int insertCnt;
			insertCnt = dao.insertOne(user);
			if (insertCnt > 0) {
				System.out.println("insert成功");
			}else {
				System.out.println("insert失敗");				
			}
		} catch (DataAccessException e) {
			//TODO 例外処理あとで実装
			e.printStackTrace();
		} catch (IOException e) {
			//TODO 例外処理あとで実装
			e.printStackTrace();
		}
	}
}