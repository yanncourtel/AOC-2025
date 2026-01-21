using System;
using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public class InvoiceCalculator(
    Dictionary<string, IDeliveryPricer> pricers,
    ITaxCalculator taxCalculator,
    ILoyaltyCalculator loyaltyCalculator)
{
    public CalculatedInvoice Calculate(EnrichedInvoice invoice, bool includeTax)
    {
        var lines = invoice.Deliveries
            .Select(d => CalculateInvoiceLine(d, includeTax))
            .ToList();

        return CalculatedInvoice.From(invoice.Customer, lines);
    }

    private InvoiceLine CalculateInvoiceLine(EnrichedDelivery delivery, bool includeTax)
    {
        var cost = CalculateCost(delivery);
        var tax = includeTax ? CalculateTax(delivery, cost) : Tax.NoTax;
        var loyaltyPoints = loyaltyCalculator.CalculateLoyaltyPoints(delivery);

        return new InvoiceLine(
            delivery.Company.Name,
            delivery.Packages,
            cost,
            tax,
            loyaltyPoints);
    }

    private Money CalculateCost(EnrichedDelivery delivery) 
        => pricers.TryGetValue(delivery.Company.Type, out var pricer) 
            ? pricer.CalculateCost(delivery) 
            : throw new InvalidOperationException($"Unknown company type: {delivery.Company.Type}");

    private Tax CalculateTax(EnrichedDelivery delivery, Money cost) 
        => taxCalculator.CalculateTaxFor(cost, delivery.Company);
}
