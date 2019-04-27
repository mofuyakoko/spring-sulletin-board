package com.example.demo.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Map<String ,Object>findOne(int id){
		String query ="SELECT * FROM WK_HELLO WHERE HELLO_NUM = ?";
		
		Map<String,Object> hello = jdbcTemplate.queryForMap(query,id);
		
		return hello;
	}
}
