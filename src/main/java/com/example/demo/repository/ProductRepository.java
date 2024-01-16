package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository //("ProductRepository")
public interface ProductRepository extends CrudRepository<Product, Long>{

}