package com.example.demo.domain.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	// ユーザーid
	// 必須
	// 50文字以下
	@NotBlank(groups = ValidGroup1.class)
	@Max(50)
	private String userId;

	// パスワード
	// 必須
	// 4文字以上、100文字以下
	// 英数字で１文字以上
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 4,max = 100 ,groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$",groups = ValidGroup3.class)
	private String password;

	// ユーザー名
	// 必須
	@NotBlank(groups = ValidGroup1.class)
	private String userName;

	// 生年月日
	// 必須
	// yyyy/MM/dd形式
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
}
