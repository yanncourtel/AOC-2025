using NorthPole.Domain;

namespace NorthPole.Calculation;

public interface ILoyaltyCalculator
{
    int CalculateLoyaltyPoints(EnrichedDelivery enriched);
}