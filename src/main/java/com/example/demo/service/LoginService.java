package com.example.demo.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.LoginDomain;
import com.example.demo.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public LoginDomain findOne(int id) {
		
		Map<String,Object> map = loginRepository.findOne(id);
		
		BigDecimal bd = (BigDecimal) map.get("hello_num");
		int hello_num = bd.intValue();
		String hello_comment = (String)map.get("hello_comment");
		
		LoginDomain ld = new LoginDomain();
		
		ld.setHello_num(hello_num);
		ld.setHello_comment(hello_comment);
		
		return ld;
	}
}
