package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

import com.example.demo.service.ProductService;

import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	
    private Timer timer;

    public ProductController(SimpleMeterRegistry simpleMeterRegistry) {
        timer = simpleMeterRegistry.timer("greetings.timer");
    }

	@RequestMapping(value = "/byId", method = RequestMethod.GET)
	public @ResponseBody Product getProductId(@RequestParam("id") Long id) {
		Timer.Sample sample = Timer.start();
		Product prod =  productService.getProductById(id);
		prod = productService.getProductByIdExternal(prod);
		double responseTimeInMilliSeconds = timer.record(() -> sample.stop(timer) / 1000000);
		logger.info("logger getById API response time: " + responseTimeInMilliSeconds + " ms");		
		return prod;
	}

	@PostMapping("/save")
	public Product createProduct(@RequestBody Product product) {//@Validated
		Timer.Sample sample = Timer.start();
		Product prod = productService.createProduct(product);
		double responseTimeInMilliSeconds = timer.record(() -> sample.stop(timer) / 1000000);
		logger.info("logger save API response time: " + responseTimeInMilliSeconds + " ms");		
		return prod;
	}
	
	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product, @RequestParam("id") Long id) {
		Timer.Sample sample = Timer.start();
		Product prod = productService.updateProduct(product,id);
		double responseTimeInMilliSeconds = timer.record(() -> sample.stop(timer) / 1000000);
		logger.info("logger update API response time: " + responseTimeInMilliSeconds + " ms");		
		return prod;
	}

}
