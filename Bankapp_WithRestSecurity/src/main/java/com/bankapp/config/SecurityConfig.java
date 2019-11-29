package com.bankapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BasicAuthenticationEntryPoint authenticationEntryPoint;
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
	
		auth.userDetailsService(userDetailsService);
	}
	@Bean
	public BCryptPasswordEncoder getEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/hello/account/**").hasAnyRole("ADMIN")
		.antMatchers("/hello/transaction/**").hasAnyRole("ADMIN","MGR")
		.antMatchers("/hello/user**").hasAnyRole("ADMIN","MGR","CLERK")
		.and()
		.httpBasic()
		.authenticationEntryPoint(authenticationEntryPoint);
		
	}

	
}










