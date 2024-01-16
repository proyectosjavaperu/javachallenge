package com.example.demo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Product;
import com.example.demo.model.ProductExternalApi;
import com.example.demo.repository.ProductRepository;
import com.example.demo.util.ProductUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProductUtil configUtil;

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(new Product(Long.valueOf(0), "Producto no existe", ""));
	}

	@Override
	public Product createProduct(Product product) {
		if (product.getName()==null) {
			return new Product(Long.valueOf(0), "Error nombre nulo", "");
		}
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product, Long id) {
		Product prodBD = productRepository.findById(id).orElse(null);
		if (prodBD==null) {
			return new Product(Long.valueOf(0), "Producto no existe", "");
		}
		if (Objects.nonNull(product.getName()) && !"".equalsIgnoreCase(product.getName())) {
			prodBD.setName(product.getName());
		}
		return productRepository.save(prodBD);
	}

	@Override
	public Product getProductByIdExternal(Product product) {
		String externalUrl = configUtil.getProperty("externalapitekton");
//		String url = "https://retoolapi.dev/BnYjoa/data/"+product.getId();
		String url = externalUrl+product.getId();
		ProductExternalApi prodExternalResponse = restTemplate.getForObject(url, ProductExternalApi.class);
		product.setMarca(prodExternalResponse.getMarca());
		return product;
	}

}
