package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {
	
	@GetMapping("/greet")
	public String getGreetMsg() {
		int i=10/0;
		return "Good Morning";
	}

}
