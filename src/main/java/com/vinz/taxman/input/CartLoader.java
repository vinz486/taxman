package com.vinz.taxman.input;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.vinz.taxman.model.Cart;


public class CartLoader
{
    @Inject
    Logger logger;

    @Inject
    ReaderFactory readerFactory;

    @Inject
    public CartLoader()
    {

    }

    public Cart load(String cartId)
    {
        try
        {
            CSVReader reader = readerFactory.getReader(cartId);

            logger.info("Rows: " + String.valueOf(reader.readAll().size()));
        }
        catch (IOException | CsvException e)
        {
            logger.severe("Unable to load cart from file: " + e.getMessage());
        }

        return null;
    }
}
