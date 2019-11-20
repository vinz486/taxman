package com.vinz.taxman.service;

import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Item;
import com.vinz.taxman.model.Receipt;

import javax.inject.Inject;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CheckoutService extends AbstractService
{
    @Inject
    IdGeneratorService idGeneratorService;

    @Inject
    SalesTaxService salesTaxService;

    @Inject
    public CheckoutService()
    {
    }

    public Receipt process(Cart cart)
    {
        Receipt receipt = initReceipt();

        List<Item> items = applyTaxes(cart);

        items.forEach(item -> {
            receipt.addToTotal(item.getTotal());
            receipt.addToTaxes(item.getTaxes());
        });

        receipt.setItems(items);

        return receipt;
    }

    private List<Item> applyTaxes(Cart cart)
    {
        return cart.getItems().stream().map(item -> salesTaxService.apply(item)).collect(toList());
    }

    private Receipt initReceipt()
    {
        Receipt receipt = new Receipt();

        receipt.setReceiptNumber(idGeneratorService.generate());

        return receipt;
    }
}
