package com.example.security;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtValidationFilter extends OncePerRequestFilter{

	@Value("${jwt.security.key}")
	private String key;
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/emphome") || request.getServletPath().equals("/addnewemployee")
				|| request.getServletPath().equals("/emplogin");
	} // these are the cases when we dont need to perform validation

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		String key = "championtheonetheonlypoundforpoundnumberonejonbonesjones";
		SecretKey shaKey = Keys.hmacShaKeyFor(key.getBytes());
		
			
		String token = request.getHeader("Authorization");
		if (token!=null) {
			
			try {
				
				Claims claims = Jwts.parser().verifyWith(shaKey).build()
									.parseSignedClaims(token).getPayload();
				
				String username = claims.get("username", String.class);
				List<String> list = claims.get("authorities", List.class);
				List<SimpleGrantedAuthority> grantedAuths = list.stream().map(aa-> new SimpleGrantedAuthority(aa)).toList(); 
				
				
				Authentication authObj = new UsernamePasswordAuthenticationToken(username, null, grantedAuths);
				SecurityContextHolder.getContext().setAuthentication(authObj);
				
			} catch (JwtException e) {
				System.out.println("Invalid token...!");
			}
		}	
			
		  filterChain.doFilter(request, response);
		
	}

	
	
	
	
	
	
	
	
	
	
}
