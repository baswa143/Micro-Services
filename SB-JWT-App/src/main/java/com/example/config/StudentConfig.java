package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.service.StudentService;

@Configuration
@EnableWebSecurity
public class StudentConfig {
	
	@Autowired 
	private StudentService studentService;
	
	@Bean
	 PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	@Bean
	 AuthenticationProvider authProvider() {
	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(studentService);
	authProvider.setPasswordEncoder(pwdEncoder());
	
	return authProvider;
	
	/*
	 * Imagine a college entrance:

StudentService = Student database clerk who finds student records.
DaoAuthenticationProvider = Security guard checking identities.
PasswordEncoder = Machine that verifies the password.
Database = Student records storage.

When a student arrives:

1.The guard asks the clerk for the student's record.
2.The clerk retrieves it from the database.
3.The guard checks the password using the verification machine.
4.If everything matches, the student is allowed inside.
	 */
	}

	@Bean
	 AuthenticationManager authManager (AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
		
	}
	
	
	
	@Bean
	 SecurityFilterChain security(HttpSecurity http) throws Exception{
		
		//CSRF (cross-site Request Forgery) attacks
		
		http
		   .authenticationProvider(authProvider())
		    .authorizeHttpRequests(req->{
			req.requestMatchers("/register","/login").permitAll()
			.anyRequest().authenticated();
			 
		});
		return http.csrf(csrf->csrf.disable()).build();//by using disable we can't allow the cookies
	}

}
