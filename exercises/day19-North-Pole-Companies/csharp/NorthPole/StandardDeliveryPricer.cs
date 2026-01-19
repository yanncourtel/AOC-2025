namespace NorthPole;

public class StandardDeliveryPricer : IDeliveryPricer
{
    private const int BaseCost = 30000;
    private const int Threshold = 50;
    private const int ThresholdBonus = 1000;
    private const int ExtraPackageCost = 300;
    private const int PerPackageCost = 200;

    public Money CalculateCost(EnrichedDelivery enriched)
    {
        var cost = BaseCost + (enriched.Packages * PerPackageCost);

        if (enriched.Packages > Threshold)
        {
            cost += ThresholdBonus + (enriched.Packages - Threshold) * ExtraPackageCost;
        }

        return Money.FromCents(cost);
    }
}