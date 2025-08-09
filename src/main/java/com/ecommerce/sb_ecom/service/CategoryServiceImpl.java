package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.repository.CategoryRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
       return categoryRepository.findAll();
    }

    @Override
    public String createCategory(Category category)
    {
        categoryRepository.save(category);
        return "The category was added successfully";
    }

    @Override
    public String deleteCategory(Long categoryId)
    {
        List<Category>categories=categoryRepository.findAll();
        Category category=categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));

        categoryRepository.delete(category);
        return "Category with id" +categoryId+ "deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId)
    {
        Optional<Category> optionalCategory=categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent())
        {
            Category existingCategory=optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category updatedCategory=categoryRepository.save(existingCategory);
            return updatedCategory;
        }
        else {
            System.out.println("Inside optional not category present");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        }
    }


}
