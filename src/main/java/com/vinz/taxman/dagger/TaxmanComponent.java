package com.vinz.taxman.dagger;

import com.vinz.taxman.Taxman;

import dagger.Component;


@Component(modules = LoggerModule.class)
public interface TaxmanComponent
{
    Taxman getTaxman();
}
