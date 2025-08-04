package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;

import java.util.List;

public interface CategoryService
{
    public List<Category> getAllCategories();
    public void createCategory(Category category);

}
