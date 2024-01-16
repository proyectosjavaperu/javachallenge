package com.example.demo.service;

import com.example.demo.model.Product;

public interface ProductService {

	Product getProductById(Long id);

	Product createProduct(Product product);

	Product updateProduct(Product product, Long id);
	
	Product getProductByIdExternal(Product product);

}
