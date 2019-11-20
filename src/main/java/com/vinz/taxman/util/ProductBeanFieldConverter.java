package com.vinz.taxman.util;

import com.opencsv.bean.AbstractBeanField;
import com.vinz.taxman.model.Product;
import com.vinz.taxman.service.SalesTaxService.Category;

public class ProductBeanFieldConverter extends AbstractBeanField<Product, Object>
{
    @Override
    protected Category convert(String value)
    {
        try {

            return Category.valueOf(value);

        } catch (IllegalArgumentException iae) {

            return Category.Default;
        }
    }
}
