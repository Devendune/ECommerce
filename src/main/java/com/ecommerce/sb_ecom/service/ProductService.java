package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Product;
import com.ecommerce.sb_ecom.payload.ProductDTO;
import com.ecommerce.sb_ecom.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public ProductDTO addProduct(ProductDTO productDTO, Long categoryId);

    List<ProductDTO> getProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchByProductName(String keyword);

    ProductDTO updatingProduct(ProductDTO productDTO, Long productId);

    public void deleteProductById(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
