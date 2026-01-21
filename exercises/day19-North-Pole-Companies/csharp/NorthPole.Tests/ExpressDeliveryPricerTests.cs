using Xunit;

namespace NorthPole.Tests;

public class ExpressDeliveryPricerTests
{
    [Theory]
    [InlineData(1, 50000)]      // Minimum
    [InlineData(50, 50000)]     // Well below threshold
    [InlineData(100, 50000)]    // Exactly at threshold
    [InlineData(101, 50500)]    // Just over threshold (1 extra package)
    [InlineData(110, 55000)]    // 10 over threshold
    [InlineData(120, 60000)]    // Real test case from order.json
    [InlineData(145, 72500)]    // Real test case from orderWithTaxes.json
    [InlineData(200, 100000)]   // Far over threshold
    public void CalculateCost_ReturnsCorrectAmount(int packages, int expectedCents)
    {
        var pricer = new ExpressDeliveryPricer();
        var delivery = CreateEnrichedDelivery("express", packages);

        var result = pricer.CalculateCost(delivery);

        Assert.Equal(Money.FromCents(expectedCents), result);
    }

    private static EnrichedDelivery CreateEnrichedDelivery(string type, int packages)
    {
        var company = new ElfCompany("Test Company", type, Region.NorthPole);
        var delivery = new Delivery("test-id", packages);
        return new EnrichedDelivery(delivery, company);
    }
}