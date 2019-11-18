package com.vinz.taxman.dagger;

import com.vinz.taxman.Taxman;

import dagger.Component;


@Component
public interface TaxmanComponent
{
    Taxman getTaxman();
}
