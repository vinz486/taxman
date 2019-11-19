package com.vinz.taxman.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product
{
    @CsvBindByName(required = true)
    private String sku;

    @CsvBindByName(required = true)
    private String category;

    @CsvBindByName(required = true)
    private String description;

    @CsvBindByName(required = true)
    private double price;

    @CsvBindByName(required = true)
    private boolean imported;
}
