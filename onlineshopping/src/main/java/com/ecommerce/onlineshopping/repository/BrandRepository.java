package com.ecommerce.onlineshopping.repository;

import com.ecommerce.onlineshopping.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    // Handle all CRUD operation for Brand entity.
}
