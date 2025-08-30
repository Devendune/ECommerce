package com.ecommerce.sb_ecom.repository;

import com.ecommerce.sb_ecom.model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByUserName(String username);


    Boolean existsByEmail(@NotBlank String name);

    Boolean existsByUserName(String name);
}
