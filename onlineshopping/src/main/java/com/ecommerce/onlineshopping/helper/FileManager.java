package com.ecommerce.onlineshopping.helper;

import java.io.IOException;
import java.util.List;

import com.ecommerce.onlineshopping.model.FileContainer;
import com.ecommerce.onlineshopping.model.Product;
import com.ecommerce.onlineshopping.repository.FileContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileManager {
    @Autowired
    private FileContainerRepository fileContainerRepository;

    public void store(MultipartFile file, Product product) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileContainer fileContainer = new FileContainer(fileName, file.getContentType(), file.getBytes());
        fileContainer.setProduct(product);
        fileContainerRepository.save(fileContainer);
    }

    public FileContainer getFile(String id) {
        return fileContainerRepository.findById(id).get();
    }

    public List<FileContainer> getAllFiles() {
        return fileContainerRepository.findAll();
    }

    public List<FileContainer> getAllImagesOfProduct(Long productId) {
        return fileContainerRepository.findImagesByProductId(productId);
    }
}
