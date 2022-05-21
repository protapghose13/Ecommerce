package com.ecommerce.onlineshopping.repository;

import com.ecommerce.onlineshopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    //  handle all CRUD operation for product table
}
