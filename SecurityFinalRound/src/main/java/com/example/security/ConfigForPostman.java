package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ConfigForPostman {

	@Autowired
	private JwtValidationFilter validationFilter;
	@Autowired
	private CustomAuthenticationEntryPoint entryPoint;
	@Autowired
	private CustomAccessDeniedHandler handler;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager)throws Exception {
		http.csrf(c-> c.disable())
			.sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationManager(authManager);
		
		http.addFilterBefore(validationFilter, BasicAuthenticationFilter.class);
		
		http.authorizeHttpRequests(req->
				req.requestMatchers("/emphome","/addnewemployee").permitAll()
				.requestMatchers("/emplogin","/findemployee/**").authenticated()
				
				.requestMatchers("/changename").hasAnyAuthority("admin", "superadmin")
				.requestMatchers("/deleteemployee/**").hasAuthority("superadmin")
				.anyRequest().authenticated()
				);
		
		// security exceptions:-
		http.exceptionHandling(ex-> ex.authenticationEntryPoint(entryPoint)
									  .accessDeniedHandler(handler));
			
		
		http.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	
	
	
}
