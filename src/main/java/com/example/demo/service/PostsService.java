package com.example.demo.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.IntStream;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.domain.model.Posts;
import com.example.demo.repository.PostsDao;

@Service
public class PostsService {

	@Autowired
	PostsDao dao;

	/**
	 * 投稿を全て取得する
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public List<Posts> selectAll() throws DataAccessException, IOException {
		List<Posts> list = new ArrayList<>();
		list = dao.selectMany();
		return list;
	}
	/**
	 * 指定条件に一致する投稿を全て取得する
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public List<Posts> selectSearchAll(Posts posts) throws DataAccessException, IOException {
		List<Posts> list = new ArrayList<>();
		list = dao.selectSearchMany(posts);
		return list;
	}

	/**
	 * ユーザIDに紐づく投稿を全て取得する
	 * 
	 * @param post
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public List<Posts> selectOneUser(Posts post) throws DataAccessException, IOException {
		List<Posts> list = dao.selectOneUser(post.getUser_id());
		return list;
	}

	/**
	 * 投稿件数を取得する
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public int selectAllCount() throws DataAccessException, IOException {
		int count = 0;
		count = dao.countAll();
		return count;
	}
	/**
	 * 指定した条件に一致する投稿件数を取得する
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public int selectSearchCount(Posts posts) throws DataAccessException, IOException {
		int count = 0;
		count = dao.countSearch(posts);
		return count;
	}
	
	/**
	 * ユーザーIDに紐づく投稿件数を取得する
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public int selectUserCount(Posts post) throws DataAccessException, IOException {
		int count = 0;
		count = dao.countOneUser(post.getUser_id());
		return count;
	}

	/**
	 * post_idに紐づく投稿を削除する
	 * 
	 * @param post
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public boolean deleteOnePost(Posts post) throws DataAccessException, IOException {
		int count = dao.deleteOne(post.getPost_id());
		boolean result = false;
		if (count > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * 新規投稿を登録する
	 * 
	 * @param post
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public boolean insertOnePost(Posts post) throws DataAccessException, IOException {
		int count = dao.insertOne(post);
		boolean result = false;
		if (count > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * ユーザーIDに紐づく投稿を取得してバイト配列で返す
	 * 
	 * @param posts
	 * @return
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public byte[] writePostsCsvFile(Posts posts) throws DataAccessException, IOException {
		DateFormat format = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
		List<Posts> list = selectOneUser(posts);
		StringBuffer sb = new StringBuffer();

		for (Posts post : list) {
			sb.append(post.getPost_id());
			sb.append(",");
			sb.append(post.getUser_id());
			sb.append(",");
			sb.append(post.getPost_text());
			sb.append(",");
			sb.append(format.format(post.getPost_date()));
			sb.append("\n");
		}
		return sb.toString().getBytes();
	}

	/**
	 * 出力CSVファイル名を作成
	 * ログインユーザ名.csv
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public String getPostsCsvFileName(String userId) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(userId);
		sb.append(".csv");
		return sb.toString();
	}
	
	private Page<Posts> getPages(Pageable pageable,List<Posts> postsList){
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Posts> displayList;
		
		if (postsList.size() < startItem) {
			displayList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, postsList.size());
            displayList = postsList.subList(startItem, toIndex);
        }
		Page<Posts> postsPages= new PageImpl<Posts>(displayList, PageRequest.of(currentPage, pageSize), postsList.size());
		return postsPages;
	}
	
	/**
	 * ページ生成処理
	 * @param page
	 * @param size
	 * @param posts
	 * @param model
	 */
	public void createPages(Optional<Integer> page,Optional<Integer> size,List<Posts> posts ,Model model) {
		// ページ生成
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
		Page<Posts> postPage = this.getPages(PageRequest.of(currentPage - 1, pageSize),posts);
		
		model.addAttribute("postPage", postPage);
		 
        int totalPages = postPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
	}
}
