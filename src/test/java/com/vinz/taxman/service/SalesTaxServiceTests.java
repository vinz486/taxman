package com.vinz.taxman.service;

import com.vinz.taxman.exception.InvalidCartException;
import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.vinz.taxman.util.ModelFactory.buildCart;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing the Receipt generation from a Cart")
public class SalesTaxServiceTests
{

    @InjectMocks
    SalesTaxService testedSalesTaxService;

    @Test
    void testOk() throws InvalidCartException
    {
        /* Prepare */
        Cart cart = buildCart(BigDecimal.valueOf(1.29), BigDecimal.valueOf(2.29), BigDecimal.valueOf(3.29));

        /* Execute */
        Item local = testedSalesTaxService.apply(cart.getItems().get(0));
        Item exempt = testedSalesTaxService.apply(cart.getItems().get(1));
        Item imported = testedSalesTaxService.apply(cart.getItems().get(2));

        /* Verify */

        // Local product
        assertThat(local.getTaxes()).isEqualByComparingTo(BigDecimal.valueOf(0.15));
        assertThat(local.getTotal()).isEqualByComparingTo(BigDecimal.valueOf(1.44));

        // Exempt product
        assertThat(exempt.getTaxes()).isEqualByComparingTo(BigDecimal.valueOf(0.0));
        assertThat(exempt.getTotal()).isEqualByComparingTo(BigDecimal.valueOf(2.29));

        // Imported product
        assertThat(imported.getTaxes()).isEqualByComparingTo(BigDecimal.valueOf(0.50));
        assertThat(imported.getTotal()).isEqualByComparingTo(BigDecimal.valueOf(3.79));
    }
}
