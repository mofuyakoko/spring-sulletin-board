package com.example.demo.domain.model;


import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import lombok.Data;

@Data
@Scope(value="session" ,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SearchForm  implements Serializable{

	private static final long serialVersionUID = -247200765948617443L;

	// ユーザーID
	private String userId;
	
	// 投稿テキスト
	private String postText;
}
