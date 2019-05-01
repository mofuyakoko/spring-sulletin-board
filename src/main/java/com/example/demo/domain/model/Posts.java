package com.example.demo.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Posts {
	
	// 投稿番号(連番)
	private int post_id;
	
	// ユーザーID
	private String user_id;
	
	// 投稿テキスト
	private String post_text;
	
	// 投稿日
	private Date post_date;
}
