using Xunit;

namespace NorthPole.Tests;

public class LoyaltyCalculatorTests
{
    private readonly LoyaltyCalculator _calculator = new();

    [Theory]
    [InlineData(1, 0)]      // Below threshold
    [InlineData(50, 0)]     // Exactly at threshold
    [InlineData(51, 1)]     // Just over threshold
    [InlineData(80, 30)]    // Real test case from order.json
    [InlineData(100, 50)]   // Round number
    public void CalculateLoyaltyPoints_StandardDelivery_ReturnsBasePoints(int packages, int expectedPoints)
    {
        var delivery = CreateEnrichedDelivery("standard", packages);

        var result = _calculator.CalculateLoyaltyPoints(delivery);

        Assert.Equal(expectedPoints, result);
    }

    [Theory]
    [InlineData(1, 0)]       // floor(1/10) = 0, max(1-50,0) = 0
    [InlineData(10, 1)]      // floor(10/10) = 1, max(10-50,0) = 0
    [InlineData(50, 5)]      // floor(50/10) = 5, max(50-50,0) = 0
    [InlineData(51, 6)]      // floor(51/10) = 5, max(51-50,0) = 1 → 5+1=6
    [InlineData(120, 82)]    // floor(120/10) = 12, max(120-50,0) = 70 → 12+70=82, real test case
    [InlineData(145, 109)]   // floor(145/10) = 14, max(145-50,0) = 95 → 14+95=109, real test case
    public void CalculateLoyaltyPoints_ExpressDelivery_ReturnsBasePointsPlusBonus(int packages, int expectedPoints)
    {
        var delivery = CreateEnrichedDelivery("express", packages);

        var result = _calculator.CalculateLoyaltyPoints(delivery);

        Assert.Equal(expectedPoints, result);
    }

    private static EnrichedDelivery CreateEnrichedDelivery(string type, int packages)
    {
        var company = new ElfCompany("Test Company", type, "test-region");
        var delivery = new Delivery("test-id", packages);
        return new EnrichedDelivery(delivery, company);
    }
}
