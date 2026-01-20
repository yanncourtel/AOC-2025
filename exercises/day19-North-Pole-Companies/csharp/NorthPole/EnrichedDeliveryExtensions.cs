using System;
using System.Collections.Generic;

namespace NorthPole;

public static class EnrichedDeliveryExtensions
{
    private static readonly Dictionary<string, IDeliveryPricer> Pricers = new()
    {
        ["express"] = new ExpressDeliveryPricer(),
        ["standard"] = new StandardDeliveryPricer()
    };
    
    private static readonly ILoyaltyCalculator LoyaltyCalculator = new LoyaltyCalculator();

    public static Money CalculateCost(this EnrichedDelivery enriched)
    {
        if (!Pricers.TryGetValue(enriched.Company.Type, out var pricer))
        {
            throw new Exception($"Unknown company type: {enriched.Company.Type}");
        }
        
        return pricer.CalculateCost(enriched);
    }
    
    public static Tax CalculateTax(this EnrichedDelivery delivery, Money cost) 
        => delivery.Company.CalculateTax(cost);

    public static int CalculateLoyaltyPoints(this EnrichedDelivery enriched) 
        => LoyaltyCalculator.CalculateLoyaltyPoints(enriched);
}