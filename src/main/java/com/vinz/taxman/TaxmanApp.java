package com.vinz.taxman;

import com.vinz.taxman.dagger.DaggerTaxmanComponent;


public class TaxmanApp
{
    public static void main(String[] args)
    {
        Taxman taxman = DaggerTaxmanComponent.create().getTaxman();

        taxman.print();
    }
}
