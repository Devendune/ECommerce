package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;

import java.util.List;

public interface CategoryService
{
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder);
    public CategoryDTO createCategory(CategoryDTO category);

    public CategoryDTO deleteCategory(Long id);
    public CategoryDTO updateCategory(CategoryDTO category,Long categoryId);

}
