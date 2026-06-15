package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.service.StudentService;

@RestController
@RequestMapping("/Api")
public class StudentRestController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "Java + SpringBoot + MicroServices";
	}
	
	@PostMapping("/register")
	public ResponseEntity<Student> register(@RequestBody Student student){
		Student saveStudent = studentService.saveStudent(student);
		return new ResponseEntity<>(saveStudent,HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Student student){
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(student.getEmail(), student.getPwd());
		
		try {
			  Authentication authenticate = authManager.authenticate(authToken);//check
			  if(authenticate.isAuthenticated()) {
				 
				  //login success
				  return new ResponseEntity<>("welcome to LoginPage",HttpStatus.OK);
			  }
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>("Invalid crediatials",HttpStatus.BAD_REQUEST);
	}
}
