package com.vinz.taxman.model;

import java.math.BigDecimal;

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

    private BigDecimal taxes;
    private BigDecimal total;

    @CsvBindByName(required = true)
    private String sku;

    @EqualsAndHashCode.Exclude
    @CsvBindByName(required = true)
    private int quantity;
}
