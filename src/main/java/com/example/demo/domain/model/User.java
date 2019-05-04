package com.example.demo.domain.model;


import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class User {
	// ユーザーid
	private String userId;
	// パスワード
	private String password;
	// ユーザー名
	private String userName;
	// 生年月日
	private Date birthday;
	// 権限
	private String role;
	// 登録日
	private Timestamp createDate;
	// 更新日
	private Timestamp updateDate;
}
