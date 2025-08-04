package com.ecommerce.sb_ecom.category;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController
{
    private CategoryService categoryService;

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
        categoryService.createCategory(category);
        return "category is created brother";
    }

    @DeleteMapping
}
