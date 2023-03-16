package com.ladecentro.service.impl;

import com.ladecentro.entity.Category;
import com.ladecentro.repository.CategoryRepository;
import com.ladecentro.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {

        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String id) {

        return categoryRepository.findById(id).get();
    }

    @Override
    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, String id) {

        return categoryRepository.save(category);
    }

    @Override
    public Object deleteCategory(String id) {

        return null;
    }
}
