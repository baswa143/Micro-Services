package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

@RestController
public class EmpRestController {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/register")
	public ResponseEntity<Employee> register(@RequestBody Employee emp){
		Employee saveEmp = empService.saveEmp(emp);
		return new ResponseEntity<>(saveEmp,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Employee emp){
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(emp.getEmail(), emp.getPwd());
		try {
		Authentication authenticate = authManager.authenticate(authToken);
		if(authenticate.isAuthenticated()) {
			return new ResponseEntity<>("welcome to Employee Login page",HttpStatus.OK);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return new ResponseEntity<>("Invalid creditionals",HttpStatus.BAD_REQUEST);
	}

}
