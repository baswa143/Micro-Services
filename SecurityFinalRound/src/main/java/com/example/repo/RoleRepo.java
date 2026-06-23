package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Integer>{

	Role findByName(String name);
	
	
	
	
}
