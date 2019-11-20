package com.vinz.taxman.service;

import com.vinz.taxman.model.Receipt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static com.vinz.taxman.util.ModelFactory.buildCart;
import static com.vinz.taxman.util.ModelFactory.buildReceipt;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing Receipt ID generator")
public class PrinterServiceTests
{

    @InjectMocks
    PrinterService testedPrinterService;

    @Test
    void testIdGeneration()
    {

        /* Prepare */
        Receipt receipt = buildReceipt(buildCart(BigDecimal.valueOf(11), BigDecimal.valueOf(22), BigDecimal.valueOf(33)).getItems(), "fakeId");

        /* Execute */
        String render = testedPrinterService.render(receipt);

        /* Verify */

        // Items are printed?
        assertThat(render).contains("Local");
        assertThat(render).contains("Exempt");
        assertThat(render).contains("Imported");

        // Receipt number is printed?
        assertThat(render).contains("fakeId");

        // Total Taxes is printed?
        assertThat(render).contains("1111.00");

        // Grand Total is printed?
        assertThat(render).contains("9999.00");

    }
}
