package com.example.demo.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
	
	public Page<Posts> getPages(Pageable pageable,List<Posts> postsList){
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
}
