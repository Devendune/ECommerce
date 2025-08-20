package com.ecommerce.sb_ecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String productName;

    private String image;

    private String description;

    @NotNull
    private int quantity;

    @NotNull
    private Double price;

    @NotNull
    private Double discount;

    private Double specialPrice;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}

