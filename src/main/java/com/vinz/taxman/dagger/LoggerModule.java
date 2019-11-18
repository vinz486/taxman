package com.vinz.taxman.dagger;

import java.util.logging.Logger;

import dagger.Module;
import dagger.Provides;


@Module
public class LoggerModule
{

    @Provides
    Logger getLogger()
    {
        return Logger.getLogger("TAXMAN");
    }
}
