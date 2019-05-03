package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.LoginDomain;
import com.example.demo.domain.model.LoginForm;
import com.example.demo.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private LoginForm sessionLoginForm;
	
	// 初期遷移 Getリクエスト
	@GetMapping("/")
	public String getRequest(@ModelAttribute LoginForm loginForm,Model model) {
		return "login";
	}

	// アドレス直打用
	@GetMapping("/login")
	public String getLogin(@ModelAttribute LoginForm loginForm,Model model) {
		return "redirect:/";

	}
	// ログイン処理 Postリクエスト
	@PostMapping("/login")
	public String postLogin(@ModelAttribute @Validated LoginForm loginForm,
			BindingResult bindingResult,Model model,RedirectAttributes redirectAttribute) {
		
		if(bindingResult.hasErrors()) {
			return getRequest(loginForm,model);
		}
		sessionLoginForm.setUserId(loginForm.getUserId());
		System.out.println(model);
		return "redirect:/topMenu";
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
