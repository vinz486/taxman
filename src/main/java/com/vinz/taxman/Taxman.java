package com.vinz.taxman;

import com.vinz.taxman.exception.InvalidCartException;
import com.vinz.taxman.input.CartLoader;
import com.vinz.taxman.model.Cart;

import javax.inject.Inject;
import java.util.logging.Logger;


public class Taxman
{
    private static final String ID_INPUT1 = "input1.csv";

    @Inject
    Logger logger;

    @Inject
    CartLoader cartLoader;

    @Inject
    Taxman()
    {

    }

    void print()
    {

        try {

            Cart cart = cartLoader.load(ID_INPUT1);

        } catch (InvalidCartException e) {

            logger.severe(e.getMessage());
        }
    }
}
