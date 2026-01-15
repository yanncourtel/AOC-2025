
using System;
using System.Globalization;
using System.Text;
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

    private static readonly CultureInfo CurrencyFormat = new("en-US");

    // Legacy methods - kept for backward compatibility
    public string Print(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => Print(EnrichInvoice(invoice, elfCompanies));

    public string PrintWithTaxes(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => PrintWithTaxes(EnrichInvoice(invoice, elfCompanies));

    // New methods - work with enriched domain model
    private string Print(EnrichedInvoice invoice)
    {
        var totalAmount = 0;
        var loyaltyPoints = 0;
        var result = new StringBuilder($"Invoice for {invoice.Customer}\n");

        foreach (var enriched in invoice.Deliveries)
        {
            var deliveryCost = CalculateDeliveryCost(enriched);

            result.AppendLine($" {enriched.Company.Name}: {FormatMoney(deliveryCost)} ({enriched.Packages} packages)");
            
            totalAmount += deliveryCost;
            loyaltyPoints += CalculateLoyaltyPoints(enriched);
        }

        result.AppendLine($"Amount owed is {FormatMoney(totalAmount)}");
        result.AppendLine($"You earned {loyaltyPoints} loyalty points");
        return result.ToString();
    }

    private string PrintWithTaxes(EnrichedInvoice invoice)
    {
        var subtotal = 0;
        var totalTax = 0;
        var loyaltyPoints = 0;
        var result = new StringBuilder($"Invoice for {invoice.Customer}\n");

        foreach (var enriched in invoice.Deliveries)
        {
            var deliveryCost = CalculateDeliveryCost(enriched);
            var tax = CalculateTax(deliveryCost, enriched.Company.Region);
            var taxInfo = GetTaxInfo(enriched.Company.Region);

            result.AppendLine($" {enriched.Company.Name}: {FormatMoney(deliveryCost)} ({enriched.Packages} packages)");
            result.AppendLine($"   Tax ({taxInfo.Name} - {taxInfo.Rate:P0}): {FormatMoney(tax)}");
            
            subtotal += deliveryCost;
            totalTax += tax;
            loyaltyPoints += CalculateLoyaltyPoints(enriched);
        }
        
        result.AppendLine($"Subtotal: {FormatMoney(subtotal)}");
        result.AppendLine($"Total Tax: {FormatMoney(totalTax)}");
        result.AppendLine($"Amount owed is {FormatMoney(subtotal + totalTax)}");
        result.AppendLine($"You earned {loyaltyPoints} loyalty points");
        
        return result.ToString();
    }

    private static EnrichedInvoice EnrichInvoice(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies)
    {
        var enrichedDeliveries = invoice.Deliveries
            .Select(delivery => new EnrichedDelivery(delivery, elfCompanies[delivery.CompanyID]))
            .ToList();
            
        return new EnrichedInvoice(invoice.Customer, enrichedDeliveries);
    }

    private static string FormatMoney(int amountInCents)
    {
        return (amountInCents / 100.0).ToString("C", CurrencyFormat);
    }

    private static (string Name, double Rate) GetTaxInfo(string region)
    {
        if (!TaxRates.TryGetValue(region, out var taxInfo))
        {
            throw new Exception($"Unknown region: {region}");
        }
        return taxInfo;
    }

    private int CalculateTax(int cost, string region)
    {
        var taxInfo = GetTaxInfo(region);
        return (int)(cost * taxInfo.Rate);
    }

    private int CalculateDeliveryCost(EnrichedDelivery enriched)
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
        return cost;
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