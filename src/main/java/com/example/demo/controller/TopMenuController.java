package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.LoginForm;
import com.example.demo.domain.model.NewPostForm;
import com.example.demo.domain.model.Posts;
import com.example.demo.domain.model.SignupForm;
import com.example.demo.service.PostsService;

@Controller
public class TopMenuController {

	@Autowired
	private PostsService postsService;
	@Autowired
	private LoginForm sessionLoginForm;

	// ログイン画面からの初期遷移
	@GetMapping("/topMenu")
	public String getTopMenu(Model model) throws DataAccessException, IOException {

		// トップ画面のhtmlを読み込ませるようにfragmentを置換する
		model.addAttribute("contents", "topMenu::menu_contents");

		// 初期表示投稿一覧取得
		List<Posts> posts = postsService.selectAll();
		model.addAttribute("postsList", posts);

		// 初期表示用件数取得
		int count = postsService.selectAllCount();
		model.addAttribute("postsCount", count);

		return "commonMenu";
	}

	// 共通メニューからマイページへの遷移
	@GetMapping("/userMyPage")
	public String getUserMyPage(Model model) throws DataAccessException, IOException {

		// トップ画面のhtmlを読み込ませるようにfragmentを置換する
		model.addAttribute("contents", "useMyPage::menu_contents");

		Posts post = new Posts();
		post.setUser_id(sessionLoginForm.getUserId());

		// 初期表示投稿一覧取得
		List<Posts> posts = postsService.selectOneUser(post);
		model.addAttribute("postsList", posts);

		// 初期表示用件数取得
		int count = postsService.selectUserCount(post);
		model.addAttribute("postsCount", count);

		return "commonMenu";
	}

	// マイページから投稿を削除
	@GetMapping("/userMyPage/delete/{id:.+}")
	public String getPostDelete(Model model, @PathVariable("id") String postId)
			throws DataAccessException, IOException {

		// トップ画面のhtmlを読み込ませるようにfragmentを置換する
		model.addAttribute("contents", "useMyPage::menu_contents");

		Posts post = new Posts();
		post.setPost_id(Integer.valueOf(postId));

		// ユーザーの削除
		postsService.deleteOnePost(post);

		return "redirect:/userMyPage";
	}

	// 共通メニューから新規投稿画面への遷移
	@GetMapping("/newPostPage")
	public String getNewPostPage(Model model,@ModelAttribute NewPostForm newPostForm) throws DataAccessException, IOException {

		// トップ画面のhtmlを読み込ませるようにfragmentを置換する
		model.addAttribute("contents", "newPost::menu_contents");

		return "commonMenu";
	}

	// 新規投稿
	@PostMapping("/newPost")
	public String postNewPostPage(Model model,@ModelAttribute NewPostForm newPostForm) throws DataAccessException, IOException {
		System.out.println(newPostForm);

		Posts post = new Posts();
		post.setUser_id(sessionLoginForm.getUserId());
		post.setPost_text(newPostForm.getPostText());
		
		postsService.insertOnePost(post);
		
		return "redirect:/userMyPage";
	}
	// ログアウト
	@PostMapping("/logout")
	public String postLogout(Model model) {
		return "redirect:/";
	}
}
