package com.vinz.taxman.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Cart
{
    private String id;

    private List<Product> products;
}
