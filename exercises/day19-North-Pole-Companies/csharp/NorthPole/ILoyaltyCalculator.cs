namespace NorthPole;

public interface ILoyaltyCalculator
{
    int CalculateLoyaltyPoints(EnrichedDelivery enriched);
}