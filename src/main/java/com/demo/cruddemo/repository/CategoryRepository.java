package com.demo.cruddemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.cruddemo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}