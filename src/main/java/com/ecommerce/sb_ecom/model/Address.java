package com.ecommerce.sb_ecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="addresses")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min=5,message="Street must be atleast 5 characters")
    private String street;

    @NotBlank
    @Size(min=4,message="buildingName must be atleast 4 characters")
    private String buildingName;

    @NotBlank
    @Size(min=4,message="city must be atleast 4 characters")
    private String city;

    @NotBlank
    @Size(min=2,message="country must be atleast 2 characters")
    private String country;

    @NotBlank
    @Size(min=6,message="Pincode must be atleast 6 characters")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addressList")
    private List<User>users=new ArrayList<>();

    public Address(String street, String buildingName, String city, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
    }
}
