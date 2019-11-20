package com.vinz.taxman.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Receipt
{
    private String receiptNumber;

    private BigDecimal grandTotal = BigDecimal.ZERO;
    private BigDecimal totalTaxes = BigDecimal.ZERO;

    private List<Item> items;

    public void addToTaxes(BigDecimal value)
    {
        totalTaxes = totalTaxes.add(value);
    }

    public void addToTotal(BigDecimal value)
    {
        grandTotal = grandTotal.add(value);
    }
}
