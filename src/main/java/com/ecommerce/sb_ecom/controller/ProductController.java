package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.model.Product;
import com.ecommerce.sb_ecom.payload.ProductDTO;
import com.ecommerce.sb_ecom.payload.ProductResponse;
import com.ecommerce.sb_ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping("/products/allProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts()
    {
        List<ProductDTO>products=productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO>addProduct(@RequestBody ProductDTO productDTO, @PathVariable Long categoryId)
    {
        ProductDTO outputProductDTO=productService.addProduct(productDTO,categoryId);
        return new ResponseEntity<>(outputProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/products/getProduct/categories/{categoryId}")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable Long categoryId)
    {
        ProductResponse productResponse=productService.searchByCategory(categoryId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
    @GetMapping("/products/getProduct/productName/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword)
    {
        ProductResponse productResponse=productService.searchByProductName(keyword);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @PutMapping("/products/updateProduct/productName/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,
                                                    @PathVariable Long productId)
    {
        ProductDTO updateProduct=productService.updatingProduct(productDTO,productId);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }

    @DeleteMapping("/product/deleteProduct/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId)
    {
    productService.deleteProductById(productId);
    return new ResponseEntity<>("The product with id"+productId+" has been deleted",HttpStatus.OK);
    }

}
