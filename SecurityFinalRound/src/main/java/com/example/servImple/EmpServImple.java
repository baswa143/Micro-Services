package com.example.servImple;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.repo.EmpRepo;
import com.example.repo.RoleRepo;

@Service
public class EmpServImple {

	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private EmpRepo empRepo;

	public void saveEmp(Employee emp) {
		emp.setPassword(encoder.encode(emp.getPassword()));
		Role role = roleRepo.findByName("normal");
		emp.setRoles(Arrays.asList(role));
		empRepo.save(emp);
	}
	
	
	
	
	
	
	
	
	
}
