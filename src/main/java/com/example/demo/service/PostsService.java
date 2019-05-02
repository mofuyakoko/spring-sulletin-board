package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Posts;
import com.example.demo.repository.PostsDao;

@Service
public class PostsService {

	@Autowired
	PostsDao dao;
	
	public List<Posts> selectMany(){
		List<Posts> list = new ArrayList<>();
		try {
			list = dao.selectMany();
			return list;
		} catch (DataAccessException e) {
			//TODO 例外処理あとで実装
			e.printStackTrace();
		} catch (IOException e) {
			//TODO 例外処理あとで実装
			e.printStackTrace();
		}
		return list;
	}
	
	public int selectAllCount(){
		int count = 0;
		try {
			count = dao.count();
			return count;
		} catch (DataAccessException e) {
			//TODO 例外処理あとで実装
			e.printStackTrace();
		} catch (IOException e) {
			//TODO 例外処理あとで実装
			e.printStackTrace();
		}
		return count;
	}

}
