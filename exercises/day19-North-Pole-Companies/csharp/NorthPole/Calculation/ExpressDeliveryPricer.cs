using NorthPole.Domain;

namespace NorthPole.Calculation;

public class ExpressDeliveryPricer : IDeliveryPricer
{
    private const int BaseCost = 50000;
    private const int Threshold = 100;
    private const int ExtraPackageCost = 500;

    public Money CalculateCost(EnrichedDelivery enriched)
    {
        if (enriched.Packages <= Threshold)
        {
            return Money.FromCents(BaseCost);
        }

        var cost = BaseCost + (enriched.Packages - Threshold) * ExtraPackageCost;
        return Money.FromCents(cost);
    }
}