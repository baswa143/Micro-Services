package com.example.service;

import org.springframework.stereotype.Service;

import com.example.exceptions.NoProductFoundException;
import com.example.model.Product;
@Service
public class ProductServiceImpl implements ProductService {
	
	

	@Override
	public Product findProductById(Integer pid) {
		if(pid == 101) {
			return new Product("101","soap",80.0);
		}
		else {
			throw new NoProductFoundException("No product found");
		}
	}

}
