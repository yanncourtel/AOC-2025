using System;
using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public class InvoiceCalculator
{
    private readonly Dictionary<string, IDeliveryPricer> _pricers;
    private readonly ITaxCalculator _taxCalculator;
    private readonly ILoyaltyCalculator _loyaltyCalculator;

    public InvoiceCalculator(
        Dictionary<string, IDeliveryPricer> pricers,
        ITaxCalculator taxCalculator,
        ILoyaltyCalculator loyaltyCalculator)
    {
        _pricers = pricers;
        _taxCalculator = taxCalculator;
        _loyaltyCalculator = loyaltyCalculator;
    }

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
        var loyaltyPoints = _loyaltyCalculator.CalculateLoyaltyPoints(delivery);

        return new InvoiceLine(
            delivery.Company.Name,
            delivery.Packages,
            cost,
            tax,
            loyaltyPoints);
    }

    private Money CalculateCost(EnrichedDelivery delivery) 
        => _pricers.TryGetValue(delivery.Company.Type, out var pricer) 
            ? pricer.CalculateCost(delivery) 
            : throw new InvalidOperationException($"Unknown company type: {delivery.Company.Type}");

    private Tax CalculateTax(EnrichedDelivery delivery, Money cost) 
        => _taxCalculator.CalculateTaxFor(cost, delivery.Company);
}
