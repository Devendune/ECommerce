package com.ecommerce.sb_ecom.repository;

import com.ecommerce.sb_ecom.model.Category;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//what is the difference between CrudRepository and JpaRepository
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
    Category findByCategoryName(@NotBlank @Size(min=5,message="Category name must contain atleast five characters") String categoryName);

}
