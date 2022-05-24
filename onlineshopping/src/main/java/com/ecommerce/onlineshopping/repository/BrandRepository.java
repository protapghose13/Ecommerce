package com.ecommerce.onlineshopping.repository;

import com.ecommerce.onlineshopping.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    // Handle all CRUD operation for Brand entity.
}
