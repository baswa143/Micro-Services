package com.example.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ProductNotFoundException;

@RestController
public class ProductRestController {
	
	@GetMapping("/product/{id}")
	public ResponseEntity<String> getProductInfo(@PathVariable Integer id){
		if(id>1000) {
			throw new ProductNotFoundException("Invalid ID");
		}
		return new ResponseEntity<>("Product is Apple",HttpStatus.OK);
	}

}
