package com.vinz.taxman.model;

import com.opencsv.bean.CsvBindByName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Item
{
    private Product product;

    private double taxes = 0;

    @CsvBindByName(required = true)
    private String sku;

    @EqualsAndHashCode.Exclude
    @CsvBindByName(required = true)
    private int quantity;
}
