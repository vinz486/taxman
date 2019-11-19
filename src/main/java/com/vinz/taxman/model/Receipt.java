package com.vinz.taxman.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Receipt
{
    private String receiptNumber;

    private List<Item> items;
}
