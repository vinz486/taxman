package com.vinz.taxman.service;

import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Receipt;

import javax.inject.Inject;

public class CheckoutService extends AbstractService
{

    @Inject
    public CheckoutService()
    {
    }

    public Receipt process(Cart cart)
    {

        return null;
    }

}
