package com.ladecentro.service.impl;

import com.ladecentro.entity.Product;
import com.ladecentro.repository.ProductRepository;
import com.ladecentro.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(String categoryId) {

        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String categoryId, String id) {

        return productRepository.findById(id).get();
    }

    @Override
    public Product createProduct(Product product, String categoryId) {

        product.setCategoryId(categoryId);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, String categoryId, String id) {

        return null;
    }

    @Override
    public Object deleteProduct(String categoryId, String id) {

        return null;
    }
}
