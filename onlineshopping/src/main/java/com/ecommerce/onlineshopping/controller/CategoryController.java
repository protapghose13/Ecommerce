package com.ecommerce.onlineshopping.controller;

import com.ecommerce.onlineshopping.model.Category;
import com.ecommerce.onlineshopping.model.Product;
import com.ecommerce.onlineshopping.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }
}
