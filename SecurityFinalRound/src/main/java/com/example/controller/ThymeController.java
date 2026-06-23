package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Employee;
import com.example.repo.EmpRepo;
import com.example.servImple.EmpServImple;

@Controller
public class ThymeController {
	
	@Autowired
	private EmpServImple service;
	

	
	@GetMapping("/")
	public String getHome(){
		return "home";
	}
	
	
	@GetMapping("/aboutus")
	public String getAboutUs() {
		return "aboutUs";
	}
	
	
	@GetMapping("/signup")
	public String getSignUp(Model model) {
		model.addAttribute("emptyObj", new Employee());
		return "signUp";
	}
	
	@PostMapping("/register")
	public String registerNew(@ModelAttribute("emptyObj") Employee emp, RedirectAttributes redirectAttributes) {
		service.saveEmp(emp);
		redirectAttributes.addFlashAttribute("success", "registration successful...!");
		return "redirect:/signup";
	}
	
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/products")
	public String getProducts() {
		return "products";
	}
	
	@GetMapping("/contactus")
	public String contactUs() {
		return "contactUs";
	}
	
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "admin";
	}
	
	
	@GetMapping("/superadmin")
	public String superAdmin() {
		return "superAdmin";
	}
	
	@GetMapping("/accessdenied")
	public String getAccessDenied() {
		return "accessDenied";
	}
	
	
	
	
	
	
	
	
}
