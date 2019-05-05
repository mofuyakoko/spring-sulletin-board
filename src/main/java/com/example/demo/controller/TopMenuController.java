package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.NewPostForm;
import com.example.demo.domain.model.Posts;
import com.example.demo.service.PostsService;

@Controller
public class TopMenuController {

	@Autowired
	private PostsService postsService;

	// ログイン画面からの初期遷移
	@GetMapping("/topMenu")
	public String getTopMenu(Authentication auth,Model model) throws DataAccessException, IOException {

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
	public String getUserMyPage(Authentication auth,Model model) throws DataAccessException, IOException {

		// トップ画面のhtmlを読み込ませるようにfragmentを置換する
		model.addAttribute("contents", "useMyPage::menu_contents");

		Posts post = new Posts();
		post.setUser_id(auth.getName());

		// 初期表示投稿一覧取得
		List<Posts> posts = postsService.selectOneUser(post);
		model.addAttribute("postsList", posts);

		// 初期表示用件数取得
		int count = postsService.selectUserCount(post);
		model.addAttribute("postsCount", count);
		
		// お試し
		postsService.writePostsCsvFile(post);
		
		return "commonMenu";
	}

	// マイページから投稿を削除
	@GetMapping("/userMyPage/delete/{id:.+}")
	public String getPostDelete(Authentication auth,Model model, @PathVariable("id") String postId)
			throws DataAccessException, IOException {

		// トップ画面のhtmlを読み込ませるようにfragmentを置換する
		model.addAttribute("contents", "useMyPage::menu_contents");

		Posts post = new Posts();
		post.setPost_id(Integer.valueOf(postId));

		// ユーザーの削除
		boolean deleteResult = postsService.deleteOnePost(post);
		if(deleteResult) {
			model.addAttribute("queryResult", "投稿を削除しました");
		} else {
			model.addAttribute("queryResult", "投稿の削除が失敗しました");
		}
		return "redirect:/userMyPage";
	}
	// マイページからCSVファイルをダウンロード
	@GetMapping("/userMyPage/csv")
	public ResponseEntity<byte[]> getCsvDownload(Authentication auth,Model model)
			throws DataAccessException, IOException {
		
		byte[] bytes = null;
		Posts posts = new Posts();
		posts.setUser_id(auth.getName());
		
		// byteデータ抽出処理
		bytes = postsService.writePostsCsvFile(posts);
		
		// レスポンスヘッダー追記処理
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv;charset=UTF-8");
		header.setContentDispositionFormData("filename", "hoge.csv");
		return new ResponseEntity<>(bytes,header,HttpStatus.OK);
	}

	// 共通メニューから新規投稿画面への遷移
	@GetMapping("/newPostPage")
	public String getNewPostPage(Authentication auth,Model model,@ModelAttribute NewPostForm newPostForm) throws DataAccessException, IOException {

		// トップ画面のhtmlを読み込ませるようにfragmentを置換する
		model.addAttribute("contents", "newPost::menu_contents");

		return "commonMenu";
	}

	// 新規投稿
	@PostMapping("/newPost")
	public String postNewPostPage(Authentication auth,Model model,@ModelAttribute NewPostForm newPostForm) throws DataAccessException, IOException {

		Posts post = new Posts();
		post.setUser_id(auth.getName());
		post.setPost_text(newPostForm.getPostText());
		
		boolean insertResult = postsService.insertOnePost(post);
		if(insertResult) {
			model.addAttribute("queryResult", "投稿しました");
		} else {
			model.addAttribute("queryResult", "投稿が失敗しました");
		}
		
		return "redirect:/userMyPage";
	}
	// ログアウト
	@PostMapping("/logout")
	public String postLogout(Model model) {
		return "redirect:/";
	}
}