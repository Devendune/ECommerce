package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private List<Category> categories=new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public String createCategory(Category category)
    {
        categories.add(category);
        return "The category was added successfully";
    }

    @Override
    public String deleteCategory(Long categoryId)
    {
        Category category=categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));

        categories.remove(category);
        return "Category with id" +categoryId+ "deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category>optionalCategory =categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst();

        if(optionalCategory.isPresent())
        {
            System.out.println("Inside optional category present");
            Category existingCategory=optionalCategory.get();
            System.out.println("The input category name is "+category.getCategoryName());
            existingCategory.setCategoryName(category.getCategoryName());
            System.out.println("The optional category name after setting is "+existingCategory.getCategoryName());
            return existingCategory;
        }
        else {
            System.out.println("Inside optional not category present");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        }
    }


}
