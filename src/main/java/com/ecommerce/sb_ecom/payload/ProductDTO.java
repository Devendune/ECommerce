package com.ecommerce.sb_ecom.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO
{
    private Long productId;
    @NotBlank
    @Size(min=5,message="The product name should br atleast 5 character")
    private String productName;
    private String image;

    @NotBlank
    private Integer quantity;

    @NotBlank
    private Double discount;

    @NotBlank
    private Double price;
    private Double specialPrice;
    private CategoryDTO categoryDTO;

}
