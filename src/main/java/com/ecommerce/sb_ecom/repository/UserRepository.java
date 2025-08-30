package com.ecommerce.sb_ecom.repository;

import com.ecommerce.sb_ecom.model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{
    User findByUserName(String username);

    Boolean existsByUsername(@NotBlank String name);

    Boolean existsByEmail(@NotBlank String name);
}
