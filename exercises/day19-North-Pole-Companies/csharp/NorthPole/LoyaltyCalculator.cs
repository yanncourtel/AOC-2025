using System;

namespace NorthPole;

public class LoyaltyCalculator : ILoyaltyCalculator
{
    public int CalculateLoyaltyPoints(EnrichedDelivery enriched)
    {
        var points = Math.Max(enriched.Packages - 50, 0);
        if (enriched.Company.Type == "express")
        {
            points += (int)Math.Floor(enriched.Packages / 10.0);
        }
        return points;
    }
}