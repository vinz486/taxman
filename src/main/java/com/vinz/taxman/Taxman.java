package com.vinz.taxman;

import com.vinz.taxman.exception.InvalidCartException;
import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Receipt;
import com.vinz.taxman.service.CartService;
import com.vinz.taxman.service.CatalogService;
import com.vinz.taxman.service.CheckoutService;
import com.vinz.taxman.service.PrinterService;

import javax.inject.Inject;
import java.util.logging.Logger;


public class Taxman
{

    private static final String ID_INPUT1 = "input1";
    private static final String ID_INPUT2 = "input2";
    private static final String ID_INPUT3 = "input3";

    @Inject
    Logger logger;

    @Inject
    CartService cartService;

    @Inject
    CatalogService catalogService;

    @Inject
    CheckoutService checkoutService;

    @Inject
    PrinterService printerService;

    @Inject
    Taxman()
    {
    }

    void print()
    {

        catalogService.load();

        Cart cart;

        try {

            cart = cartService.load(ID_INPUT1);
            catalogService.resolve(cart);
            emitReceipt(cart);


            cart = cartService.load(ID_INPUT2);
            catalogService.resolve(cart);
            emitReceipt(cart);

            cart = cartService.load(ID_INPUT3);
            catalogService.resolve(cart);
            emitReceipt(cart);

        } catch (InvalidCartException e) {

            logger.severe(e.getMessage());
        }
    }

    private void emitReceipt(Cart cart)
    {
        Receipt receipt = checkoutService.process(cart);

        System.out.println(printerService.render(receipt));
    }
}
