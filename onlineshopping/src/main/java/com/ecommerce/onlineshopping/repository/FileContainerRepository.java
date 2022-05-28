package com.ecommerce.onlineshopping.repository;

import com.ecommerce.onlineshopping.model.FileContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileContainerRepository extends JpaRepository<FileContainer, String> {
    @Query(
            value = "SELECT * FROM file_container u WHERE u.product_id = ?1",
            nativeQuery = true
    )
    List<FileContainer> findImagesByProductId(Long productId);
}
