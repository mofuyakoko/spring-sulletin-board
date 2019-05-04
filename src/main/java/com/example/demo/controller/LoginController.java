package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.domain.model.LoginForm;

@Controller
public class LoginController {
	
	// 初期遷移 Getリクエスト
	@GetMapping("/")
	public String getRequest(@ModelAttribute LoginForm loginForm,Model model) {
		return "login";
	}
	@GetMapping("/login")
	public String getLoginRequest(@ModelAttribute LoginForm loginForm,Model model) {
		return "redirect:/";
	}
}
