package com.ecommerce.sb_ecom.category;

import com.ecommerce.sb_ecom.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController
{
    private List<Category> categories=new ArrayList<>();

    @GetMapping("/api/public/categories")
    public List<Category> getAllCategories()
    {
        return categories;
    }

    @PostMapping("/api/admin/createCategory")
    public String createCategory(@RequestBody Category category)
    {
        return "The category was created successfully";
    }
}
