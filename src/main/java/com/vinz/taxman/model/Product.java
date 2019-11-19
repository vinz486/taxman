package com.vinz.taxman.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

@Getter
public class Product
{
    @CsvBindByName(required = true)
    private String id;

    @CsvBindByName(required = true)
    private int quantity;

    @CsvBindByName(required = true)
    private String description;

    @CsvBindByName(required = true)
    private double price;

    @CsvBindByName(required = true)
    private boolean imported;
}
