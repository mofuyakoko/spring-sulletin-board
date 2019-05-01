package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.Posts;
import com.example.demo.service.PostsService;

@Controller
public class TopMenuController {
	
	@Autowired
	PostsService postsService;
	
	@GetMapping("/topMenu")
	public String getTopMenu(Model model) {
		
		// トップ画面のhtmlを読み込ませるようにfragmentを置換する
		model.addAttribute("contents", "topMenu::menu_contents");
		
		// 初期表示投稿一覧取得
		List<Posts> posts = postsService.selectMany();
		model.addAttribute("postsList",posts);
		
		// 初期表示用件数取得
		int count = postsService.selectCount();
		model.addAttribute("postsCount",count);

		return "commonMenu";
	}

	@PostMapping("/logout")
	public String postLogout(Model model) {
		return "redirect:/";
	}
}
