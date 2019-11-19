package com.vinz.taxman.input;

import com.vinz.taxman.model.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing loading from CSV")
public class CartLoaderTests
{

    @InjectMocks
    CartLoader testedCartLoader;

    @Spy
    ReaderFactory spiedReaderFactory;

    @Test
    void testLoader()
    {

        /* Prepare */
        String cartId = "testCart1.csv";

        /* Execute */
        Cart loaded = testedCartLoader.load(cartId);

        /* Verify */

        // Loaded the right id?
        verify(spiedReaderFactory).getReader(cartId);

        // Return same id?
        assertThat(loaded.getId()).isEqualTo(cartId);

        // Same size?
        assertThat(loaded.getProducts().size()).isEqualTo(3);

        // Right content?
        assertThat(loaded.getProducts().get(0).getId()).isEqualTo("P01");
        assertThat(loaded.getProducts().get(1).getId()).isEqualTo("P02");
        assertThat(loaded.getProducts().get(2).getId()).isEqualTo("P03");
    }
}
