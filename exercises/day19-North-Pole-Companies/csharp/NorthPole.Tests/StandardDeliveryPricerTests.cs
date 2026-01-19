
using Xunit;

namespace NorthPole.Tests;

public class StandardDeliveryPricerTests
{
    [Theory]
    [InlineData(1, 30200)]      // Minimum (base 30000 + 1*200)
    [InlineData(10, 32000)]     // Below threshold (30000 + 10*200)
    [InlineData(50, 40000)]     // Exactly at threshold (30000 + 50*200)
    [InlineData(51, 41500)]     // Just over threshold (30000 + 1000 + 1*300 + 51*200)
    [InlineData(60, 46000)]     // 10 over threshold (30000 + 1000 + 10*300 + 60*200)
    [InlineData(80, 56000)]     // Real test case from order.json (30000 + 1000 + 30*300 + 80*200)
    [InlineData(160, 96000)]    // Real test case from orderWithTaxes.json (30000 + 1000 + 110*300 + 160*200)
    [InlineData(200, 116000)]   // Large order (30000 + 1000 + 150*300 + 200*200)
    public void CalculateCost_ReturnsCorrectAmount(int packages, int expectedCents)
    {
        // Arrange
        var pricer = new StandardDeliveryPricer();
        var delivery = CreateEnrichedDelivery("standard", packages);

        // Act
        var result = pricer.CalculateCost(delivery);

        // Assert
        Assert.Equal(Money.FromCents(expectedCents), result);
    }

    private static EnrichedDelivery CreateEnrichedDelivery(string type, int packages)
    {
        var company = new ElfCompany("Test Company", type, "test-region");
        var delivery = new Delivery("test-id", packages);
        return new EnrichedDelivery(delivery, company);
    }
}