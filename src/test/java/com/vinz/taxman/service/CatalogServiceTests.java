package com.vinz.taxman.service;

import com.vinz.taxman.factory.ReaderFactory;
import com.vinz.taxman.model.Cart;
import com.vinz.taxman.model.Item;
import com.vinz.taxman.model.Product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing loading Catalog from CSV")
public class CatalogServiceTests
{

    @InjectMocks
    CatalogService testedCatalogService;

    @Spy
    ReaderFactory spiedReaderFactory;

    @Test
    void testLoaderOk()
    {

        /* Prepare */

        /* Execute */
        testedCatalogService.load();

        /* Verify */

        // Loaded the right file?
        verify(spiedReaderFactory).getReader("catalog", Product.class);

        // Loaded right content?
        assertThat(testedCatalogService.get("PILL01").getDescription()).isEqualTo("Packet of Headache Pills");
    }

    @Test
    void testResolver()
    {
        /* Prepare */
        Item item = new Item();
        item.setSku("PILL01");
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        Cart cart = Cart.builder().id("TEST01").items(items).build();

        /* Execute */
        testedCatalogService.load();
        testedCatalogService.resolve(cart);

        /* Verify */
        assertThat(cart.getItems().get(0).getProduct().getSku()).isEqualTo("PILL01");
    }
}
