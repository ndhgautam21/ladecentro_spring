package com.ladecentro.controller;

import com.ladecentro.entity.Product;
import com.ladecentro.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get/{category_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllProducts(@PathVariable("category_id") String categoryId) {

        return ResponseEntity.ok(productService.getAllProducts(categoryId));
    }

    @GetMapping("/get/{category_id}/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProduct(@PathVariable("category_id") String categoryId,
                                        @PathVariable("id") String id) {

        return ResponseEntity.ok(productService.getProduct(categoryId, id));
    }

    @PostMapping("/create/{category_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody Product product,
                                           @PathVariable("category_id") String categoryId) {

        return ResponseEntity.ok(productService.createProduct(product, categoryId));
    }

    @PutMapping("/update/{category_id}/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@RequestBody Product product,
                                           @PathVariable("category_id") String categoryId,
                                           @PathVariable("id") String id) {

        return ResponseEntity.ok(productService.updateProduct(product, categoryId, id));
    }

    @DeleteMapping("/delete/{category_is}/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable("category_id") String categoryId,
                                           @PathVariable("id") String id) {

        return ResponseEntity.ok(productService.deleteProduct(categoryId, id));
    }
}
