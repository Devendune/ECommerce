package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.model.Product;
import com.ecommerce.sb_ecom.payload.ProductDTO;
import com.ecommerce.sb_ecom.payload.ProductResponse;
import com.ecommerce.sb_ecom.repository.CategoryRepository;
import com.ecommerce.sb_ecom.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ProductDTO addProduct(Product product, Long categoryId)
    {
        Optional<Category> category=categoryRepository.findById(categoryId);
        if(category.isEmpty())
            throw new ResourceNotFoundException("category","categoryId","category");

        product.setCategory(category.get());
        Double discountedPrice=product.getPrice()* product.getDiscount()*0.01;
        Double specialPrice=product.getPrice()-discountedPrice;

        product.setSpecialPrice(specialPrice);
        product.setImage("default");

        Product savedProduct=productRepository.save(product);

        return modelMapper.map(savedProduct,ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product>products=productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId)
    {
        Optional<Category> category=categoryRepository.findById(categoryId);
        if(category.isEmpty())
            throw new ResourceNotFoundException("Category","category","id");

        List<Product>products=productRepository.findByCategoryOrderByPriceAsc(category.get());
        List<ProductDTO>responses=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();

        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(responses);
        return productResponse;
    }
}
