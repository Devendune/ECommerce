package com.ecommerce.sb_ecom.exceptions;

public class ProductAlreadyExistsException extends RuntimeException
{
    public ProductAlreadyExistsException()
    {

    }
    public ProductAlreadyExistsException(String message)
    {
        super(message);
    }
}
