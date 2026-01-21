using System;
using Xunit;

namespace NorthPole.Tests;

public class TaxCalculatorTests
{
    private readonly TaxCalculator _calculator = new();

    [Theory]
    [InlineData("north-pole", 0)]      // 0% tax
    [InlineData("nordic", 1500)]       // 15% of 10000
    [InlineData("alpine", 2000)]       // 20% of 10000
    [InlineData("arctic", 1000)]       // 10% of 10000
    public void CalculateTaxFor_ReturnsCorrectAmount(string region, int expectedTaxCents)
    {
        var company = new ElfCompany("Test Company", "standard", region);
        var cost = Money.FromCents(10000); // $100

        var result = _calculator.CalculateTaxFor(cost, company);

        Assert.Equal(Money.FromCents(expectedTaxCents), result.Amount);
    }

    [Theory]
    [InlineData("north-pole", "Tax (North Pole - 0%): $0.00")]
    [InlineData("nordic", "Tax (Nordic Region - 15%): $15.00")]
    [InlineData("alpine", "Tax (Alpine Region - 20%): $20.00")]
    [InlineData("arctic", "Tax (Arctic Region - 10%): $10.00")]
    public void CalculateTaxFor_ReturnsCorrectDescription(string region, string expectedDescription)
    {
        var company = new ElfCompany("Test Company", "standard", region);
        var cost = Money.FromCents(10000); // $100

        var result = _calculator.CalculateTaxFor(cost, company);

        Assert.Equal(expectedDescription, result.ToString());
    }

    [Fact]
    public void CalculateTaxFor_WithUnknownRegion_ThrowsException()
    {
        var company = new ElfCompany("Test Company", "standard", "unknown-region");
        var cost = Money.FromCents(10000);

        var exception = Assert.Throws<Exception>(() => _calculator.CalculateTaxFor(cost, company));

        Assert.Contains("Unknown region", exception.Message);
        Assert.Contains("unknown-region", exception.Message);
    }
}
