package com.demo.cruddemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.cruddemo.entity.Product;
import com.demo.cruddemo.repository.ProductRepository;

	@RestController
	@RequestMapping("/api/products")
	public class ProductController {
	    private final ProductRepository productRepository;

	    public ProductController(ProductRepository productRepository) {
	        this.productRepository = productRepository;
	    }

	    
	    //GET all the products
	    @GetMapping
	    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
	        Pageable pageable = PageRequest.of(page, 10);
	        return productRepository.findAll(pageable);
	    }

	    
	    //POST - create a new product
	    @PostMapping
	    public Product createProduct(@RequestBody Product product) {
	        return productRepository.save(product);
	    }

	    
	    //GET product by Id
	    @GetMapping("/{id}")
	    public Product getProductById(@PathVariable Long id) {
	        Product product = productRepository.findById(id)
	                .orElseThrow();
	        product.getCategory();

	        return product;
	    }

	    //PUT - update product by id
	    @PutMapping("/{id}")
	    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
	        Product product = productRepository.findById(id)
	                .orElseThrow();

	        product.setName(productDetails.getName());
	        product.setPrice(productDetails.getPrice());
	        return productRepository.save(product);
	    }

	    
	    //DELETE - Delete product by id
	    @DeleteMapping("/{id}")
	    public void deleteProduct(@PathVariable Long id) {
	        Product product = productRepository.findById(id)
	                .orElseThrow();

	        productRepository.delete(product);
	    }
	}


