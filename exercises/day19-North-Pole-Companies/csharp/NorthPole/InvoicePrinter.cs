
using System;
using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public class InvoicePrinter
{
    private static readonly Dictionary<string, (string Name, double Rate)> TaxRates = new()
    {
        ["north-pole"] = ("North Pole", 0.0),
        ["nordic"] = ("Nordic Region", 0.15),
        ["alpine"] = ("Alpine Region", 0.20),
        ["arctic"] = ("Arctic Region", 0.10)
    };
    
    private readonly IInvoiceFormatter _formatter;
    private readonly IInvoiceFormatter _formatterWithTax;

    public InvoicePrinter(IInvoiceFormatter formatter, IInvoiceFormatter formatterWithTax)
    {
        _formatter = formatter;
        _formatterWithTax = formatterWithTax;
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
        var cost = CalculateDeliveryCost(enriched);
        var tax = includeTax ? CalculateTax(cost, enriched.Company.Region) : (Tax?)null;
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

    private static (string Name, double Rate) GetTaxInfo(string region)
    {
        if (!TaxRates.TryGetValue(region, out var taxInfo))
        {
            throw new Exception($"Unknown region: {region}");
        }
        return taxInfo;
    }

    private Tax CalculateTax(Money cost, string region)
    {
        var taxInfo = GetTaxInfo(region);
        var taxAmount = cost * taxInfo.Rate;
        return new Tax(taxInfo.Name, taxInfo.Rate, taxAmount);
    }

    private Money CalculateDeliveryCost(EnrichedDelivery enriched)
    {
        var cost = 0;
        switch (enriched.Company.Type)
        {
            case "express":
                cost = 50000;
                if (enriched.Packages > 100)
                {
                    cost += 500 * (enriched.Packages - 100);
                }
                break;
            case "standard":
                cost = 30000;
                if (enriched.Packages > 50)
                {
                    cost += 1000 + 300 * (enriched.Packages - 50);
                }
                cost += 200 * enriched.Packages;
                break;
            default:
                throw new Exception($"unknown type: {enriched.Company.Type}");
        }
        return Money.FromCents(cost);
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