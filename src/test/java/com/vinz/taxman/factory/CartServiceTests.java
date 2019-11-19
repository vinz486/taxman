package com.vinz.taxman.factory;

import com.vinz.taxman.exception.InvalidCartException;
import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Item;
import com.vinz.taxman.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing loading from CSV")
public class CartServiceTests
{

    @InjectMocks
    CartService testedCartService;

    @Spy
    ReaderFactory spiedReaderFactory;

    @Test
    void testLoaderOk() throws InvalidCartException
    {

        /* Prepare */
        String cartId = "testcart1";

        /* Execute */
        Cart loaded = testedCartService.load(cartId);

        /* Verify */

        // Loaded the right id?
        verify(spiedReaderFactory).getReader(cartId, Item.class);

        // Return same id?
        assertThat(loaded.getId()).isEqualTo(cartId);

        // Same size?
        assertThat(loaded.getItems().size()).isEqualTo(3);

        // Right content?
        assertThat(loaded.getItems().get(0).getSku()).isEqualTo("BOOK01");
        assertThat(loaded.getItems().get(1).getSku()).isEqualTo("MUCD01");
        assertThat(loaded.getItems().get(2).getSku()).isEqualTo("CHOC01");
    }

    @Test()
    void testLoaderInvalid()
    {

        /* Prepare */
        String cartId = "testinvalid1";

        /* Execute */
        assertThatThrownBy(() -> testedCartService.load(cartId))
                /* Verify */
                .isInstanceOf(InvalidCartException.class)
                .hasMessageContaining(cartId);
    }
}
