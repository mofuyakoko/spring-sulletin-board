package com.example.demo.controller;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.GroupOrder;
import com.example.demo.domain.model.SignupForm;
import com.example.demo.domain.model.User;
import com.example.demo.service.UserService;

@Controller
public class SignupController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm signupForm,Model model) {
		return "signup";
	}

	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form
			,BindingResult bindingResult,Model model)throws DataAccessException, IOException {
		// 入力チェック
		if(bindingResult.hasErrors()) {
			return getSignUp(form,model);
		}
			
		User user = new User();
		
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setRole("ROLE_GENERAL");
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));
		user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		
		userService.insertNewUser(user);
		
		return "redirect:/";
	}

}
