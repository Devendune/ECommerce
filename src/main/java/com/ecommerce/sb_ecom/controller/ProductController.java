package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController
{
    @GetMapping("/produc")
    public ResponseEntity<List<ProductDTO>>getProducts()
    {

    }

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO>addProduct(@RequestBody ProductDTO productDTO, @PathVariable Long categoryId)
    {

    }
}
