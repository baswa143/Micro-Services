package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	public Student findByEmail(String email);

}
