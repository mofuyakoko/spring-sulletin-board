package com.example.demo.domain.model;


import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class NewPostForm  implements Serializable{

	private static final long serialVersionUID = 2406320300490107356L;
	// 投稿内容
	// 必須
	// 100文字以下
	@NotBlank
	@Length(max = 100)
	private String postText;
}
