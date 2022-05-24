package com.ecommerce.onlineshopping.repository;

import com.ecommerce.onlineshopping.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Handle all CRUD operation for Category entity.
}
