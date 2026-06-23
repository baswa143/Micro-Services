package com.example.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
		
		
		Map<String, Object> error = new HashMap<>();
		error.put("status", 403);
		error.put("error", "Yor are forbidden from accessing this");
		error.put("message", accessDeniedException.getMessage());
		error.put("path", request.getRequestURI());
		error.put("timestamp", LocalDateTime.now().toString());
		
		mapper.writeValue(response.getOutputStream(), error);
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
}
