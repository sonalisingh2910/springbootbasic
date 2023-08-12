package com.spring.security.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig  {
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService()
	{
//		UserDetails normalUser =User
//				.withUsername("Sonali")
//				.password(passwordEncoder().encode("password"))
//				.roles("NORMAL")
//				.build();
//		
//		UserDetails adminUser=User.withUsername("sona")
//				.password(passwordEncoder().encode("password"))
//				.roles("ADMIN")
//				.build();
//		
//		 InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(normalUser, adminUser);
//		
//		return inMemoryUserDetailsManager;
		return new CustomUserDetailsService();
		
	}
	
	
	@Bean
	public DefaultSecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception
	{
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		//agar apko ye chej nhi karni hai  to ap isko method level pe laga sakte ho
//		.requestMatchers("/home/admin")
//		.hasRole("ADMIN")
//		
//		.requestMatchers("/home/normal")
//		.hasRole("NORMAL")
//		
//		.requestMatchers("/home/public")
//		.permitAll()
		
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		
		return httpSecurity.build();
	}

}
