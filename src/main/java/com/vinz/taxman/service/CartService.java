package com.vinz.taxman.service;

import com.opencsv.bean.CsvToBean;
import com.vinz.taxman.exception.InvalidCartException;
import com.vinz.taxman.factory.ReaderFactory;
import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Item;

import javax.inject.Inject;
import java.util.List;


public class CartService extends AbstractService
{
    @Inject
    ReaderFactory readerFactory;

    @Inject
    public CartService()
    {
    }

    public Cart load(String cartId) throws InvalidCartException
    {

        try {

            CsvToBean<Item> reader = readerFactory.getReader(cartId, Item.class);

            return buildCart(reader.parse(), cartId);

        } catch (RuntimeException re) {

            throw new InvalidCartException(cartId, re.getCause());
        }
    }

    private static Cart buildCart(List<Item> parsed, String cartId)
    {
        return Cart.builder().id(cartId).items(parsed).build();
    }
}
