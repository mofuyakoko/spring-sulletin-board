package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.util.DmlType;
import com.example.demo.util.FileIoUtil;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	private DataSource dataSource;
	@Autowired
	private FileIoUtil util;
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/webjars/**","/css/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		// 直リンク許可
		http.authorizeRequests().antMatchers("/webjars/**").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/signup").permitAll()
		.anyRequest().authenticated();
		
		// ログイン成功時
		http.formLogin()
		.loginProcessingUrl("/login")
		.loginPage("/login")
		.failureUrl("/")
		.usernameParameter("userId")
		.passwordParameter("password")
		.defaultSuccessUrl("/topMenu",true);
		
		// ログアウト処理
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutUrl("/logout")
		.logoutSuccessUrl("/");
		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(util.readSqlFile("selectLoginUser", DmlType.SELECT))
		.authoritiesByUsernameQuery(util.readSqlFile("selectLoginUserRole", DmlType.SELECT))
		.passwordEncoder(passwordEncoder());
	}
}
