package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emp_info")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String city;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "emp_roles",
			   joinColumns = @JoinColumn(name="emp_id", referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
			)
	private List<Role> roles= new ArrayList<>();
	
	
	
	
	
	
	
	
	
	
}
