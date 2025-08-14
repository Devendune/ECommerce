package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.config.AppConstants;
import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
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

    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name="pageNumber" , defaultValue= AppConstants.PAGE_NUMBER,required = false)  Integer pageNumber,
                                              @RequestParam(name="pageSize", defaultValue=AppConstants.PAGE_SIZE,required = false) Integer pageSize)
    {
        return new ResponseEntity<>("The pageNumber: "+ pageNumber+" The pageSize is "+pageSize,HttpStatus.OK);
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name="pageNumber" , defaultValue= AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                             @RequestParam(name="pageSize" , defaultValue=AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                             @RequestParam(name="sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy,
                                                             @RequestParam(name="sortOrder",defaultValue = AppConstants.SORT_DIR,required = false) String sortOrder)
    {
        CategoryResponse categoryResponse=categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @PostMapping("/api/admin/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category)
    {
        CategoryDTO categoryDTO=categoryService.createCategory(category);
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/deleteCategory/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId)
    {
            CategoryDTO categoryDTO = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }
    @PutMapping("/api/admin/updateCategory/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO category,@PathVariable Long categoryId)
    {
            CategoryDTO updatedCategory=categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }
}
