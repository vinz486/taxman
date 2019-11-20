package com.vinz.taxman.service;

import com.vinz.taxman.model.Item;
import com.vinz.taxman.model.Receipt;

import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthFixedColumns;
import de.vandermeer.asciitable.v2.row.ContentRow;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.List;

public class PrinterService extends AbstractService
{
    @Inject
    public PrinterService()
    {
    }

    public void render(Receipt receipt)
    {
        V2_AsciiTable table = new V2_AsciiTable();

        table.addRule();
        table.addRow(null, null, "Receipt ID\n" + receipt.getReceiptNumber()).setAlignment(new char[]{'c', 'c', 'c'});

        table.addRule();
        renderDetails(table, receipt.getItems());

        table.addRule();
        renderTaxes(table, receipt.getTotalTaxes());

        table.addStrongRule();
        renderTotal(table, receipt.getGrandTotal());

        table.addRule();
        print(table);
    }

    private static void renderTotal(V2_AsciiTable table, BigDecimal grandTotal)
    {
        table.addRow(null, null, String.format("Total %.2f", grandTotal)).setAlignment(new char[]{'c', 'c', 'r'});
    }

    private static void renderTaxes(V2_AsciiTable table, BigDecimal totalTaxes)
    {
        table.addRow(null, null, String.format("Sales Taxes %.2f", totalTaxes)).setAlignment(new char[]{'c', 'c', 'r'});
    }

    private static void renderDetails(V2_AsciiTable table, List<Item> items)
    {
        String[] details;
        ContentRow contentRow;

        char[] align = new char[]{'r', 'r', 'r'};

        for (Item item : items) {

            details = new String[3];

            details[0] = String.format("%d x", item.getQuantity());

            String description = item.getProduct().getDescription();

            if (item.getProduct().isImported()) {
                details[1] = String.format("(imported) %s", description);
            } else {
                details[1] = description;
            }

            details[2] = String.format("%.2f", item.getTotal());

            contentRow = new ContentRow(details, 0);

            table.addRow(contentRow.getColumns()).setAlignment(align);
        }
    }

    private static void print(V2_AsciiTable table)
    {
        V2_AsciiTableRenderer renderer = new V2_AsciiTableRenderer();
        renderer.setTheme(V2_E_TableThemes.UTF_LIGHT_DOUBLE.get());

        WidthFixedColumns width = new WidthFixedColumns();
        width.add(6).add(30).add(8);

        renderer.setWidth(width);

        System.out.println(renderer.render(table));
    }
}
