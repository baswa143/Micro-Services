package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.csrf(c-> c.disable());
//		
//		http.authorizeHttpRequests(req->
//				req.requestMatchers("/","/aboutus","/signup","/register","/login",
//						"/contactus","/handlelogin", "/accessdenied").permitAll()
//					.requestMatchers("/products").authenticated()
//					
//					.requestMatchers("/admin").hasAnyAuthority("admin", "superadmin")
//					.requestMatchers("/superadmin").hasAuthority("superadmin")
//					
//					.anyRequest().authenticated()
//				);
//		
//		
//		http.exceptionHandling(ex-> ex.accessDeniedPage("/accessdenied"));
//		
//		http.formLogin(f-> f.loginPage("/login").loginProcessingUrl("/handlelogin")
//							.defaultSuccessUrl("/products").failureUrl("/login"));
//		http.httpBasic(Customizer.withDefaults());
//		
//		return http.build();
//	}
	
	
	@Bean
	AuthenticationManager manager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	
}
