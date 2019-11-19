package com.vinz.taxman.input;

import com.opencsv.bean.CsvToBean;
import com.vinz.taxman.exception.InvalidCartException;
import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Product;

import javax.inject.Inject;
import java.util.List;


public class CartLoader
{
    @Inject
    ReaderFactory readerFactory;

    @Inject
    public CartLoader()
    {

    }

    public Cart load(String cartId) throws InvalidCartException
    {
        CsvToBean<Product> reader = readerFactory.getReader(cartId);

        try {

            return buildCart(reader.parse(), cartId);

        } catch (RuntimeException re) {

            throw new InvalidCartException(cartId, re.getCause());
        }
    }

    private Cart buildCart(List<Product> parsed, String cartId)
    {
        return Cart.builder().id(cartId).products(parsed).build();
    }
}
