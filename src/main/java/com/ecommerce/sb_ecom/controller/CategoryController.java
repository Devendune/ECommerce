package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController
{
    private CategoryService categoryService;
    long currId=1;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public List<Category> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @PostMapping("/api/admin/createCategory")
    public String createCategory(@RequestBody Category category)
    {
        category.setCategoryId(currId++);
        categoryService.createCategory(category);
        return "category is created brother";
    }

    @DeleteMapping("/api/admin/deleteCategory/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId)
    {
        return categoryService.deleteCategory(categoryId);
    }
}
