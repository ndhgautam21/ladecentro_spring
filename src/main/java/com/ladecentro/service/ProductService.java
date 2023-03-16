package com.ladecentro.service;

import com.ladecentro.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts(String categoryId);

    Product getProduct(String categoryId, String id);

    Product createProduct(Product product, String categoryId);

    Product updateProduct(Product product, String categoryId, String id);

    Object deleteProduct(String categoryId, String id);
}
