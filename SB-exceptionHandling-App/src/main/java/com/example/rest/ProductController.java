package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Product;
import com.example.service.ProductService;

@RestController
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	@GetMapping(value="/getProduct", produces= {"application/json"})
	public Product getProductDataById(@RequestParam("Pid") String pid) {
		return productService.findProductById(Integer.parseInt(pid));
	}

}
