package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Product;
import com.ecommerce.sb_ecom.payload.ProductDTO;
import com.ecommerce.sb_ecom.payload.ProductResponse;

import java.util.List;

public interface ProductService {
    public ProductDTO addProduct(Product product, Long categoryId);

    List<ProductDTO> getProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchByProductName(String keyword);

    ProductDTO updatingProduct(Product product, Long productId);
}
