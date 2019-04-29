package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.LoginDomain;
import com.example.demo.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	// 初期遷移 Getリクエスト
	@GetMapping("/")
	public String getLogin() {
		return "login";
	}

	// 初期遷移 Postリクエスト
	@PostMapping("/")
	public String postLogin() {
		return "login";
	}

	// ログイン処理
	@PostMapping("/login")
	public String postRequest(@RequestParam("userText")String str, Model model) {
		model.addAttribute("sample", str);
		
		return "loginResponse";
	}

	// DB接続テスト用
	@PostMapping("/login/db")
	public String postDbRequest(@RequestParam("id")String str, Model model) {

		int id = Integer.parseInt(str);
		LoginDomain ld = loginService.findOne(id);

		model.addAttribute("id", ld.getHello_num());
		model.addAttribute("comment",ld.getHello_comment());
		return "loginDbResponse";
	}

}
