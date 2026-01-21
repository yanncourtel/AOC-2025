using NorthPole.Domain;

namespace NorthPole.Calculation;

public interface IDeliveryPricer
{
    Money CalculateCost(EnrichedDelivery delivery);
}