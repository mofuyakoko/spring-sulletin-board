package com.example.demo.repository.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Posts;
import com.example.demo.repository.PostsDao;
import com.example.demo.util.DmlType;
import com.example.demo.util.FileIoUtil;

@Repository
public class PostsDaoImpl implements PostsDao {

	@Autowired
	FileIoUtil util;
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int countAll() throws DataAccessException, IOException {
		int count = jdbc.queryForObject(util.readSqlFile("selectPostsCount", DmlType.SELECT), Integer.class);
		return count;
	}

	@Override
	public int countOneUser(String userId) throws DataAccessException, IOException {
		int count = jdbc.queryForObject(util.readSqlFile("selectPostsCountOneUser", DmlType.SELECT),Integer.class,userId);
		return count;
	}

	@Override
	public int insertOne(Posts posts) throws DataAccessException, IOException {
		return 0;
	}

	@Override
	public Posts selectOne(String post_id) throws DataAccessException, IOException {
		return null;
	}

	@Override
	public List<Posts> selectMany() throws DataAccessException, IOException {
		List<Map<String, Object>> getList = jdbc.queryForList(util.readSqlFile("selectPostsAll", DmlType.SELECT));
		List<Posts> postsList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			Posts posts = new Posts();
			
			posts.setPost_id((Integer)map.get("POST_ID"));
			posts.setUser_id((String)map.get("USER_ID"));
			posts.setPost_text((String)map.get("POST_TEXT"));
			posts.setPost_date((Timestamp)map.get("POST_DATE"));
			
			postsList.add(posts);
		}

		return postsList;
	}
	
	@Override
	public List<Posts> selectOneUser(String userId) throws DataAccessException, IOException {
		List<Map<String, Object>> getList = jdbc.queryForList(util.readSqlFile("selectPostsOneUser", DmlType.SELECT),userId);
		List<Posts> postsList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			Posts posts = new Posts();
			
			posts.setPost_id((Integer)map.get("POST_ID"));
			posts.setUser_id((String)map.get("USER_ID"));
			posts.setPost_text((String)map.get("POST_TEXT"));
			posts.setPost_date((Timestamp)map.get("POST_DATE"));
			
			postsList.add(posts);
		}

		return postsList;
	}

	@Override
	public int updateOne(Posts posts) throws DataAccessException, IOException {
		return 0;
	}

	@Override
	public int deleteOne(int post_id) throws DataAccessException, IOException {
		String sql = util.readSqlFile("deletePostsOne", DmlType.DELETE);
		int result = jdbc.update(sql, post_id);
		return result;

	}

	@Override
	public void postsCsvOut() throws DataAccessException, IOException {
		// TODO 自動生成されたメソッド・スタブ

	}
}
