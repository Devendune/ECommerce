package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<List<Category>> getAllCategories()
    {
        List<Category>categories=categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("/api/admin/createCategory")
    public ResponseEntity<String> createCategory(@RequestBody Category category)
    {
        category.setCategoryId(currId++);
        String status=categoryService.createCategory(category);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/deleteCategory/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {
        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status,HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
    @PutMapping("/api/admin/updateCategory/{categoryId}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category,@PathVariable Long categoryId)
    {
        try{
            Category updatedCategory=categoryService.updateCategory(category,categoryId);
            System.out.println("The received category name is "+updatedCategory.getCategoryName());
            return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(null,e.getStatusCode());
        }
    }
}
