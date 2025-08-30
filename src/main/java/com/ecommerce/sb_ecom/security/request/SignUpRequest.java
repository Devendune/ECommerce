package com.ecommerce.sb_ecom.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest
{
    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Size(max=50)
    private String email;

    private Set<String>roles;

    @NotBlank
    @Size(min=5,max=50)
    private String password;



}
