package com.example.service;




import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repo.StudentRepo;

@Service
public class StudentService implements UserDetailsService{

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private PasswordEncoder pwdEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// TODO Auto-generated method stub
		Student student=studentRepo.findByEmail(email);
		return new User(student.getEmail( ),student.getPwd(),Collections.emptyList());
	}
	
	public Student saveStudent(Student student) {
		
		String encodedPwd = pwdEncoder.encode(student.getPwd());
		
		//override original pwd with encoded pwd before saving
		student.setPwd(encodedPwd);
		return studentRepo.save(student);
	}
}
