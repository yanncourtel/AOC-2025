namespace PortalStabilization.Models;

public record KarmaBalance(
    string Dimension,
    int TotalKarmaDebt,
    int AnomalyCount)
{
    public double AverageKarmaDebt()
    {
        return AnomalyCount == 0 ? 0.0 : (double)TotalKarmaDebt / AnomalyCount;
    }
}
