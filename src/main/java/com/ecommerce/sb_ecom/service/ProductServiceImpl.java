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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ProductDTO addProduct(ProductDTO productDTO, Long categoryId)
    {
        Product product=modelMapper.map(productDTO,Product.class);
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

    @Override
    public ProductResponse searchByProductName(String keyword)
    {
        List<Product> productList=productRepository.findByProductNameLikeIgnoreCase(keyword);
        if(productList.isEmpty())
            throw new ResourceNotFoundException("Product","Product","Product");

        List<ProductDTO>responses=productList.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();

        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(responses);
        return productResponse;
    }

    @Override
    public ProductDTO updatingProduct(ProductDTO productDTO, Long productId)
    {
        Product product=modelMapper.map(productDTO,Product.class);
        Optional<Product> fetchedProductOpt=productRepository.findById(productId);
        if(fetchedProductOpt.isEmpty())
            throw new ResourceNotFoundException("Product","product","id");

        Product fetchedProduct=fetchedProductOpt.get();
        fetchedProduct.setProductName(product.getProductName());
        fetchedProduct.setDescription(product.getDescription());
        fetchedProduct.setQuantity(product.getQuantity());
        fetchedProduct.setDiscount(product.getDiscount());
        fetchedProduct.setPrice(product.getPrice());

        Product updatedProduct= productRepository.save(fetchedProduct);
        return modelMapper.map(updatedProduct,ProductDTO.class);
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        //Get the product from DB
        Optional<Product> productDB=productRepository.findById(productId);
        if(productDB.isEmpty())
            throw new ResourceNotFoundException("Product","product","id");

        String path="/images";
        String imageName=uploadImage(path,image);
        productDB.get().setImage(imageName);
        Product product=productRepository.save(productDB.get());
        return modelMapper.map(product,ProductDTO.class);
    }

    private String uploadImage(String path, MultipartFile file) throws IOException {
        String originalFileName=file.getName();
        String randomId= UUID.randomUUID().toString();
        String fileName=randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        String filePath=path+ File.pathSeparator+file;

        File folder=new File(path);
        if(!folder.exists())
            folder.mkdir();

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }
}
