package com.demo.cruddemo.controller;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import com.demo.cruddemo.entity.Category;
import com.demo.cruddemo.repository.CategoryRepository;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //GET all the categories
    @GetMapping
    public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10); // Set the page size to 10
        return categoryRepository.findAll(pageable);
    }

    //POST - create a new category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    //GET category by Id
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }

    
    //PUT - update category by id
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow();

        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }

    
    //DELETE - Delete category by id
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow();

        categoryRepository.delete(category);
    }
}

