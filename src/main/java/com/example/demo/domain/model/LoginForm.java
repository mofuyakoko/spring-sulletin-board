package com.example.demo.domain.model;


import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope(value="session" ,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginForm  implements Serializable{
	
	private static final long serialVersionUID = -578833673609264424L;

	// ユーザーid
	// 必須
	@NotBlank
	private String userId;

	// パスワード
	// 必須
	@NotBlank
	private String password;

}
