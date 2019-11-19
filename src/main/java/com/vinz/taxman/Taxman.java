package com.vinz.taxman;

import com.vinz.taxman.input.CartLoader;
import com.vinz.taxman.model.Cart;

import javax.inject.Inject;


public class Taxman
{

    private static final String ID_INPUT1 = "input1.csv";

    @Inject
    CartLoader cartLoader;

    @Inject
    Taxman()
    {

    }

    void print()
    {

        Cart cart = cartLoader.load(ID_INPUT1);
    }
}
