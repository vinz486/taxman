package com.vinz.taxman.input;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.vinz.taxman.model.Product;

import javax.inject.Inject;
import java.io.InputStreamReader;


public class ReaderFactory
{

    @Inject
    ReaderFactory()
    {

    }

    public CsvToBean<Product> getReader(String inputId)
    {

        CsvToBeanBuilder<Product> csvToBeanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(this.getClass().getResourceAsStream("/" + inputId)));

        csvToBeanBuilder.withType(Product.class).withIgnoreLeadingWhiteSpace(true);

        return csvToBeanBuilder.build();
    }
}
