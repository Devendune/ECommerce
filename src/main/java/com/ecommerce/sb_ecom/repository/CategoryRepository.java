package com.ecommerce.sb_ecom.repository;

import com.ecommerce.sb_ecom.model.Category;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//what is the difference between CrudRepository and JpaRepository
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{

}
