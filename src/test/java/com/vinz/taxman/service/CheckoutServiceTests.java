package com.vinz.taxman.service;

import com.vinz.taxman.exception.InvalidCartException;
import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Item;
import com.vinz.taxman.model.Product;
import com.vinz.taxman.model.Receipt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.vinz.taxman.service.SalesTaxService.Category.Default;
import static com.vinz.taxman.service.SalesTaxService.Category.Medical;
import static com.vinz.taxman.util.ModelFactory.buildCart;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing the Receipt generation from a Cart")
public class CheckoutServiceTests
{

    @InjectMocks
    CheckoutService testedCheckoutService;

    @Spy
    IdGeneratorService spiedIdGenerator;

    @Spy
    SalesTaxService spiedSalesTaxService;

    @Test
    void testOk() throws InvalidCartException
    {

        /* Prepare */
        Cart cart = buildCart(BigDecimal.valueOf(1.29), BigDecimal.valueOf(2.29), BigDecimal.valueOf(3.29));
        when(spiedIdGenerator.generate()).thenReturn("generatedTestId");

        /* Execute */
        Receipt receipt = testedCheckoutService.process(cart);

        /* Verify */

        // Assigned the right Id?
        assertThat(receipt.getReceiptNumber()).isEqualTo("generatedTestId");

        // Right number of Items?
        assertThat(receipt.getItems().size()).isEqualTo(cart.getItems().size());

        // Right Items?
        assertThat(receipt.getItems().get(0)).isEqualTo(cart.getItems().get(0));
        assertThat(receipt.getItems().get(1)).isEqualTo(cart.getItems().get(1));
        assertThat(receipt.getItems().get(2)).isEqualTo(cart.getItems().get(2));

        // Right Total Taxes? (local: 1.29 -> 0.15 / exempt: 2.29 -> 0 / imported: 3.29 -> 0.35 + 0.20 -> 0.50)
        // Expected = 0.15 + 0 + 0.50 = 0.65
        assertThat(receipt.getTotalTaxes()).isEqualByComparingTo(BigDecimal.valueOf(0.65));

        // Right Grand Total? (local: 1.29 + 0.15 / exempt: 2.29 + 0 / imported: 3.29 + 0.50)
        // Expected = 7.52
        assertThat(receipt.getGrandTotal()).isEqualByComparingTo(BigDecimal.valueOf(7.52));

    }
}
