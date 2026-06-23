package com.example.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.repo.EmpRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EmpRepo empRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee foundEmp = empRepo.findByEmail(username);
		if (foundEmp==null) throw new RuntimeException("Invalid Username.....!");
		
		List<Role> roles = foundEmp.getRoles();
		List<SimpleGrantedAuthority> grantedRoles = roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).toList();
		
		return new User(foundEmp.getEmail(), foundEmp.getPassword(), grantedRoles);
	}                                        // which is in encoded form

	
	
	
	
	
	
	
	
	
	
	
	
	
}
