package com.demo.cruddemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.cruddemo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
