package com.vinz.taxman.input;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import com.opencsv.CSVReader;


public class ReaderFactory
{

    @Inject
    ReaderFactory()
    {

    }

    public CSVReader getReader(String inputId) throws FileNotFoundException
    {

        return new CSVReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + inputId)));
    }
}
