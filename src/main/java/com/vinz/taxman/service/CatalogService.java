package com.vinz.taxman.service;

import com.opencsv.bean.CsvToBean;
import com.vinz.taxman.factory.ReaderFactory;
import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Item;
import com.vinz.taxman.model.Product;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CatalogService extends AbstractService
{

    private static final String CATALOG_NAME = "catalog";

    @Inject
    ReaderFactory readerFactory;

    private Map<String, Product> catalog;

    @Inject
    public CatalogService()
    {
    }

    public Product get(String sku)
    {
        return catalog.get(sku);
    }

    public void load()
    {
        CsvToBean<Product> catalogReader = readerFactory.getReader(CATALOG_NAME, Product.class);

        catalog = catalogReader.parse().stream().collect(Collectors.toMap(Product::getSku, p -> p));
    }

    public void resolve(Cart cart)
    {
        List<Item> collected = cart.getItems().stream().map(this::resolveProducts).collect(toList());

        cart.setItems(collected);
    }

    private Item resolveProducts(Item item)
    {
        Product product = get(item.getSku());

        if (product == null) {

            throw new RuntimeException("Product not found " + item.getSku());
        }

        item.setProduct(product);

        return item;
    }
}
