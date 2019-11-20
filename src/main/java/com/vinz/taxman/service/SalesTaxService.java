package com.vinz.taxman.service;

import com.vinz.taxman.model.Item;

import javax.inject.Inject;

import java.math.BigDecimal;

import static com.vinz.taxman.service.SalesTaxService.Taxes.*;


public class SalesTaxService extends AbstractService
{
    public interface Taxes
    {

        BigDecimal TAX_ROUND_QUANTUM = BigDecimal.valueOf(0.05);

        BigDecimal TAX_EXEMPTION = BigDecimal.ZERO;
        BigDecimal TAX_IMPORTED = BigDecimal.valueOf(0.05);
        BigDecimal TAX_DEFAULT = BigDecimal.valueOf(0.10);
    }

    public enum Category
    {
        Book(TAX_EXEMPTION),
        Food(TAX_EXEMPTION),
        Medical(TAX_EXEMPTION),

        Default(TAX_DEFAULT);

        private BigDecimal rate = BigDecimal.ZERO;

        Category(BigDecimal localTaxRate)
        {
            rate = localTaxRate;
        }

        public BigDecimal getRate()
        {
            return rate;
        }
    }

    @Inject
    public SalesTaxService()
    {
    }

    public Item apply(Item item)
    {
        BigDecimal total = item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
        BigDecimal taxes = applyTaxes(item, total);

        total = total.add(taxes);

        item.setTotal(total);
        item.setTaxes(taxes);

        return item;
    }

    private BigDecimal applyTaxes(Item item, BigDecimal total)
    {
        BigDecimal taxes = BigDecimal.ZERO;

        if (item.getProduct().isImported()) {

            taxes = taxes.add(total.multiply(TAX_IMPORTED));
        }

        taxes = taxes.add(total.multiply(item.getProduct().getCategory().getRate()));

        return taxRound(taxes);
    }

    public BigDecimal taxRound(BigDecimal input)
    {
        BigDecimal numberOfParts = input.divide(TAX_ROUND_QUANTUM, BigDecimal.ROUND_UP);

        return numberOfParts.setScale(0, BigDecimal.ROUND_UP).multiply(TAX_ROUND_QUANTUM);
    }
}
