package com.ecommerce.onlineshopping.controller;

import com.ecommerce.onlineshopping.exception.ResourceNotFoundException;
import com.ecommerce.onlineshopping.model.Product;
import com.ecommerce.onlineshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product employee){
        return productRepository.save(employee);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exists with id : " + id));

        return ResponseEntity.ok(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails){
        Product updateProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not exists with id : " + id));

        // TODO: user mapper
        updateProduct.setName(productDetails.getName());
        updateProduct.setBrandId(productDetails.getBrandId());
        updateProduct.setCategoryId(productDetails.getCategoryId());
        updateProduct.setDescription(productDetails.getDescription());
        updateProduct.setCode(productDetails.getCode());
        updateProduct.setQuantity(productDetails.getQuantity());
        updateProduct.setUnitPrice(productDetails.getUnitPrice());

        productRepository.save(updateProduct);

        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id : " + id));

        productRepository.delete(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
