namespace NorthPole;

public interface IDeliveryPricer
{
    Money CalculateCost(EnrichedDelivery delivery);
}