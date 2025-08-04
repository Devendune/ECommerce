package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private List<Category> categories=new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category)
    {
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId)
    {
        Category category=categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst().orElse(null);

        categories.remove(category);

        if(category==null)
            return "Category with id"+categoryId+"not found";

        else
            return "Category with id" +categoryId+ "deleted";

    }
}
