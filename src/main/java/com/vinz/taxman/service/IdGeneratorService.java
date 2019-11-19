package com.vinz.taxman.service;

import javax.inject.Inject;
import java.util.UUID;

public class IdGeneratorService extends AbstractService
{
    @Inject
    public IdGeneratorService()
    {
    }

    /**
     * This should be a legal ID, usually in the form of $CURRENT_YEAR/$INCREMENTAL_NUMBER
     *
     * @return a unique ID for receipts
     */
    public String generate()
    {
        return UUID.randomUUID().toString();
    }
}
