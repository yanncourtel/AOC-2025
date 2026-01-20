
using System;
using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public class InvoicePrinter
{
    private readonly IInvoiceFormatter _formatter;
    private readonly IInvoiceFormatter _formatterWithTax;
    private readonly ITaxCalculator _taxCalculator;

    public InvoicePrinter() 
        : this(
            new TextInvoiceFormatter(), 
            new TextInvoiceWithTaxFormatter(),
            new TaxCalculator())
    {
    }

    private InvoicePrinter(
        IInvoiceFormatter formatter, 
        IInvoiceFormatter formatterWithTax,
        ITaxCalculator taxCalculator)
    {
        _formatter = formatter;
        _formatterWithTax = formatterWithTax;
        _taxCalculator = taxCalculator;
    }
    
    // Legacy methods - kept for backward compatibility
    public string Print(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => Print(
            EnrichInvoice(invoice, elfCompanies));

    private string Print(EnrichedInvoice invoice) 
        => _formatter.Format(
            CalculateInvoice(invoice, includeTax: false));

    public string PrintWithTaxes(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => PrintWithTaxes(
            EnrichInvoice(invoice, elfCompanies));

    private string PrintWithTaxes(EnrichedInvoice invoice) 
        => _formatterWithTax.Format(
            CalculateInvoice(invoice, includeTax: true));

    private CalculatedInvoice CalculateInvoice(EnrichedInvoice invoice, bool includeTax)
    {
        var lines = invoice.Deliveries
            .Select(d => CalculateInvoiceLine(d, includeTax))
            .ToList();

        return CalculatedInvoice.From(invoice.Customer, lines);
    }

    private InvoiceLine CalculateInvoiceLine(EnrichedDelivery enriched, bool includeTax)
    {
        var cost = enriched.CalculateCost();
        var tax = includeTax ? _taxCalculator.CalculateTax(cost, enriched.Company.Region) : (Tax?)null;
        var loyaltyPoints = CalculateLoyaltyPoints(enriched);

        return new InvoiceLine(
            enriched.Company.Name,
            enriched.Packages,
            cost,
            tax,
            loyaltyPoints
        );
    }

    private static EnrichedInvoice EnrichInvoice(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies)
    {
        var enrichedDeliveries = invoice.Deliveries
            .Select(delivery => new EnrichedDelivery(delivery, elfCompanies[delivery.CompanyID]))
            .ToList();
            
        return new EnrichedInvoice(invoice.Customer, enrichedDeliveries);
    }

    private int CalculateLoyaltyPoints(EnrichedDelivery enriched)
    {
        var points = Math.Max(enriched.Packages - 50, 0);
        if (enriched.Company.Type == "express")
        {
            points += (int)Math.Floor(enriched.Packages / 10.0);
        }
        return points;
    }
}