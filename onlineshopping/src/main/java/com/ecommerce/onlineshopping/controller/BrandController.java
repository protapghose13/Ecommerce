package com.ecommerce.onlineshopping.controller;

import com.ecommerce.onlineshopping.model.Brand;
import com.ecommerce.onlineshopping.model.Category;
import com.ecommerce.onlineshopping.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/brands")
public class BrandController {
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping
    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    @PostMapping
    public Brand createBrand(@RequestBody Brand brand){
        return brandRepository.save(brand);
    }
}
