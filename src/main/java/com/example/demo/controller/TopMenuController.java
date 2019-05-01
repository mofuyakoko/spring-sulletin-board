package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopMenuController {
	
	@GetMapping("/topMenu")
	public String getTopMenu(Model model) {
		
		// トップ画面のhtmlを読み込ませるようにfragmentを置換する
		model.addAttribute("contents", "topMenu::menu_contents");
		return "CommonMenu";
	}

	@PostMapping("/logout")
	public String postLogout(Model model) {
		return "redirect:/";
	}
}
