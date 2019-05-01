package com.example.demo.domain.model;


import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

	// ユーザーid
	// 必須
	@NotBlank
	private String userId;

	// パスワード
	// 必須
	@NotBlank
	private String password;

}
