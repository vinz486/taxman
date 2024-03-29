package com.vinz.taxman.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.vinz.taxman.service.SalesTaxService.Category;
import com.vinz.taxman.util.ProductBeanFieldConverter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product
{
    @CsvBindByName(required = true)
    private String sku;

    @CsvCustomBindByName(converter = ProductBeanFieldConverter.class)
    private Category category;

    @CsvBindByName(required = true)
    private String description;

    @CsvBindByName(required = true)
    private BigDecimal price;

    @CsvBindByName(required = true)
    private boolean imported;
}
