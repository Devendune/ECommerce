package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.exceptions.APIException;
import com.ecommerce.sb_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private List<Category> categories=new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize)
    {
        Pageable pageDetails= PageRequest.of(pageNumber,pageSize);
        Page<Category> categoryPage=categoryRepository.findAll(pageDetails);
        List<Category>categories=categoryPage.getContent();

        if(categories.size()==0)
            throw new APIException("No Categories are present,Please create one");

        List<CategoryDTO> categoryDTOS=categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse=new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
       return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO)
    {
        Category category=modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDB=categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDB!=null)
            throw new APIException("The Category with the name "+category.getCategoryName()+" already exists Please try another name");

        Category savedCategory=categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId)
    {
        Optional<Category> deletedCategory=categoryRepository.findById(categoryId);
        if(deletedCategory.isPresent())
        {
        categoryRepository.delete(deletedCategory.get());
        return modelMapper.map(deletedCategory.get(),CategoryDTO.class);
        }
        throw new APIException("The categoryId "+categoryId+" is not present");
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO category, Long categoryId)
    {
        Optional<Category> optionalCategory=categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent())
        {
            Category existingCategory=optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category savedResponse= categoryRepository.save(existingCategory);
            return modelMapper.map(savedResponse,CategoryDTO.class);
        }
        else {
            System.out.println("Inside optional not category present");
            throw new ResourceNotFoundException("Category","updation","model");
        }
    }


}
