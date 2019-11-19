package com.vinz.taxman.input;

import com.opencsv.bean.CsvToBean;
import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Product;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;


public class CartLoader
{
    @Inject
    Logger logger;

    @Inject
    ReaderFactory readerFactory;

    @Inject
    public CartLoader()
    {

    }

    public Cart load(String cartId)
    {
        CsvToBean<Product> reader = readerFactory.getReader(cartId);

        return buildCart(reader.parse(), cartId);
    }

    private Cart buildCart(List<Product> parsed, String cartId)
    {

        return Cart.builder().id(cartId).products(parsed).build();

    }
}
