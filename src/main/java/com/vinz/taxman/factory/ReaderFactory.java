package com.vinz.taxman.factory;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import javax.inject.Inject;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReaderFactory
{

    @Inject
    ReaderFactory()
    {
    }

    public <RECORD> CsvToBean<RECORD> getReader(String inputName, Class<? extends RECORD> clazz)
    {

        String inputPath = String.format("/%s.csv", inputName.toLowerCase());

        InputStream resource = getClass().getResourceAsStream(inputPath);

        if (resource == null) {

            throw new RuntimeException("Cannot find input id " + inputName);
        }

        CsvToBeanBuilder<RECORD> csvToBeanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(resource));

        csvToBeanBuilder.withType(clazz).withIgnoreLeadingWhiteSpace(true);

        return csvToBeanBuilder.build();
    }
}
