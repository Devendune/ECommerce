package com.ecommerce.sb_ecom.exceptions;

public class ResourceNotFoundException extends RuntimeException
{
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException()
    {

    }
    public ResourceNotFoundException(String resourceName)
    {
        super(String.format("The resource %s is not found",resourceName));
        this.resourceName=resourceName;

    }

}
