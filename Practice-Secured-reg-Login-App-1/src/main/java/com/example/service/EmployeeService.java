package com.example.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepo;

@Service
public class EmployeeService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private PasswordEncoder pwdEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Employee emp = empRepo.findByEmail(email);
		return new User(emp.getEmail(),emp.getPwd(),Collections.emptyList());
	}
	
	public Employee saveEmp(Employee emp) {
		String encodePwd = pwdEncoder.encode(emp.getPwd());
		emp.setPwd(encodePwd);
		return empRepo.save(emp);
	}

}
