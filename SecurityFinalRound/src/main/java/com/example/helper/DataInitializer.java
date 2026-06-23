package com.example.helper;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.repo.EmpRepo;
import com.example.repo.RoleRepo;

@Component
public class DataInitializer implements CommandLineRunner{

	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private EmpRepo empRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		// firstly adding all three roles to the roles table
		addRoleIfNotExist("admin");
		addRoleIfNotExist("superadmin");
		addRoleIfNotExist("normal");
		
		// adding babloo as admin
		addAdmin("vijay");
		
		
		// adding myself as the super admin
		addSuperAdmin("chetan");
	}
	
	
	

	private void addSuperAdmin(String string) {
		String adminEmail = "chetan@gmail.com";
		
		Role adminRole = roleRepo.findByName("admin");
		Role superRole = roleRepo.findByName("superadmin");
		
		Employee foundEmp = empRepo.findByEmail(adminEmail);
		if (foundEmp==null) {
			Employee emp = new Employee();
			emp.setName(string);
			emp.setEmail(adminEmail);
			emp.setPassword(encoder.encode("chetan"));
			emp.setCity("pune");
			emp.setRoles(Arrays.asList(adminRole, superRole));
			empRepo.save(emp);
		}
	}




	private void addAdmin(String string) {
		String adminEmail = "vijay@gmail.com";
		Role adminRole = roleRepo.findByName("admin");
		Employee foundEmp = empRepo.findByEmail(adminEmail);
		if (foundEmp==null) {
			Employee emp = new Employee();
			emp.setName(string);
			emp.setEmail(adminEmail);
			emp.setPassword(encoder.encode("vijay"));
			emp.setCity("pune");
			emp.setRoles(Arrays.asList(adminRole));
			empRepo.save(emp);
		}
	}




	private void addRoleIfNotExist(String string) {
		Role foundRole = roleRepo.findByName(string);
		if (foundRole==null) {
			Role role = new Role();
			role.setName(string);
			roleRepo.save(role);
		}
	}

}
