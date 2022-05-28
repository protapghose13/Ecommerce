package com.ecommerce.onlineshopping.service;

import com.ecommerce.onlineshopping.exception.ResourceNotFoundException;
import com.ecommerce.onlineshopping.helper.FileManager;
import com.ecommerce.onlineshopping.model.Brand;
import com.ecommerce.onlineshopping.model.Category;
import com.ecommerce.onlineshopping.model.FileContainer;
import com.ecommerce.onlineshopping.model.Product;
import com.ecommerce.onlineshopping.repository.BrandRepository;
import com.ecommerce.onlineshopping.repository.CategoryRepository;
import com.ecommerce.onlineshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private FileManager fileManager;

    private Brand getBrandById(Long brandId){
        return brandRepository.getReferenceById(brandId);
    }

    private Category getCategoryById(Long categoryId){
        return categoryRepository.getReferenceById(categoryId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exists with id : " + id));

        return product;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product updateProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not exists with id : " + id));

        // TODO: use mapper
        updateProduct.setName(productDetails.getName());
        updateProduct.setDescription(productDetails.getDescription());
        updateProduct.setCode(productDetails.getCode());
        updateProduct.setQuantity(productDetails.getQuantity());
        updateProduct.setUnitPrice(productDetails.getUnitPrice());

        updateProduct.setBrand(getBrandById(productDetails.getBrandId()));
        updateProduct.setCategory(getCategoryById(productDetails.getCategoryId()));

        return productRepository.save(updateProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id : " + id));

        productRepository.delete(product);
    }

    public Product createProduct(Product product) {
        Category category = getCategoryById(product.getCategoryId());
        Brand brand = getBrandById(product.getBrandId());

        product.setCategory(
                new Category(
                        category.getId(),
                        category.getName(),
                        category.getDescription()
                )
        );
        product.setBrand(
                new Brand(
                        brand.getId(),
                        brand.getName(),
                        brand.getDescription()
                )
        );

        return productRepository.save(product);
    }

    public void saveImageOfProduct(MultipartFile file, Long productId) throws IOException {
        fileManager.store(file, productRepository.getReferenceById(productId));
    }

    public List<String> getAllImagesOfProduct(Long productId){
        List<FileContainer> files = fileManager.getAllImagesOfProduct(productId);

        List<String> base64Images = new ArrayList<>();
        for(FileContainer file : files){
            String t = Base64.getEncoder().encodeToString(file.getContent());
            base64Images.add(t);
        }

        return base64Images;
    }

    public Page<Product> getAllProductsOfPage(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(page);
    }
}
