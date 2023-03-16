package com.ladecentro.service;

import com.ladecentro.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    Category getCategory(String id);

    Category createCategory(Category category);

    Category updateCategory(Category category, String id);

    Object deleteCategory(String id);

}
