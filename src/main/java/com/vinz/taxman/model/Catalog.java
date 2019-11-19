package com.vinz.taxman.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Catalog
{
    private Map<String, Product> products = new HashMap<>();
}
