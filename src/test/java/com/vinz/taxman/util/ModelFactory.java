package com.vinz.taxman.util;

import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Item;
import com.vinz.taxman.model.Product;
import com.vinz.taxman.model.Receipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.vinz.taxman.service.SalesTaxService.Category.Default;
import static com.vinz.taxman.service.SalesTaxService.Category.Medical;

public class ModelFactory
{

    public static Receipt buildReceipt(List<Item> items, String receiptNumber)
    {
        Receipt receipt = new Receipt();

        receipt.setItems(items);
        receipt.setReceiptNumber(receiptNumber);

        receipt.setGrandTotal(BigDecimal.valueOf(9999));
        receipt.setTotalTaxes(BigDecimal.valueOf(1111));

        return receipt;
    }

    public static Cart buildCart(BigDecimal localPrice, BigDecimal exemptPrice, BigDecimal importedPrice)
    {
        List<Item> items = new ArrayList<>(3);

        Item item;
        Product product;

        product = getLocalProduct(localPrice);
        item = new Item();
        item.setQuantity(1);
        item.setProduct(product);
        item.setSku(product.getSku());
        items.add(item);

        product = getExemptProduct(exemptPrice);
        item = new Item();
        item.setQuantity(1);
        item.setProduct(product);
        item.setSku(product.getSku());
        items.add(item);

        product = getImportedProduct(importedPrice);
        item = new Item();
        item.setQuantity(1);
        item.setProduct(product);
        item.setSku(product.getSku());
        items.add(item);

        return Cart.builder().id("testcart").items(items).build();
    }

    public static Product getExemptProduct(BigDecimal exemptPrice)
    {
        Product product = new Product();

        product.setSku("exempt");
        product.setPrice(exemptPrice);
        product.setDescription("Exempt Product");
        product.setCategory(Medical);
        product.setImported(false);

        return product;
    }

    public static Product getLocalProduct(BigDecimal localPrice)
    {
        Product product = new Product();

        product.setSku("local");
        product.setPrice(localPrice);
        product.setDescription("Local Product");
        product.setCategory(Default);
        product.setImported(false);

        return product;
    }

    public static Product getImportedProduct(BigDecimal importedPrice)
    {
        Product product = new Product();

        product.setSku("imported");
        product.setPrice(importedPrice);
        product.setDescription("Imported Product");
        product.setCategory(Default);
        product.setImported(true);

        return product;
    }
}
