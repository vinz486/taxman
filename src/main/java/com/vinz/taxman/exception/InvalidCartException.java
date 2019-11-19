package com.vinz.taxman.exception;

/**
 * The processed cart is invalid and cannot be processed
 */
public class InvalidCartException extends Exception
{

    public InvalidCartException(String cartId, Throwable cause)
    {
        super(String.format("The Cart with id %s is invalid and cannot be processed", cartId), cause);
    }

    @Override
    public String getMessage()
    {
        return super.getMessage() + " > " + getCause().getMessage();
    }
}
