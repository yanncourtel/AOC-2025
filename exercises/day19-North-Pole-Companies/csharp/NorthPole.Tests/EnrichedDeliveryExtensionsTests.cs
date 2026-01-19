using Xunit;

namespace NorthPole.Tests;

public class EnrichedDeliveryExtensionsTests
{
    [Fact]
    public void CalculateCost_WithExpressType_UsesExpressPricer()
    {
        // Arrange
        var delivery = CreateEnrichedDelivery("express", 120);

        // Act
        var result = delivery.CalculateCost();

        // Assert
        Assert.Equal(Money.FromCents(60000), result);
    }

    [Fact]
    public void CalculateCost_WithStandardType_UsesStandardPricer()
    {
        // Arrange
        var delivery = CreateEnrichedDelivery("standard", 80);

        // Act
        var result = delivery.CalculateCost();

        // Assert
        Assert.Equal(Money.FromCents(56000), result);
    }

    [Fact]
    public void CalculateCost_WithUnknownType_ThrowsException()
    {
        // Arrange
        var delivery = CreateEnrichedDelivery("unknown", 100);

        // Act & Assert
        var exception = Assert.Throws<System.Exception>(() => delivery.CalculateCost());
        Assert.Contains("Unknown company type", exception.Message);
        Assert.Contains("unknown", exception.Message);
    }

    private static EnrichedDelivery CreateEnrichedDelivery(string type, int packages)
    {
        var company = new ElfCompany("Test Company", type, "test-region");
        var delivery = new Delivery("test-id", packages);
        return new EnrichedDelivery(delivery, company);
    }
}