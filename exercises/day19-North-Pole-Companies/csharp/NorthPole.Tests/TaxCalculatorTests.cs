using NorthPole.Calculation;
using NorthPole.Domain;
using Xunit;

namespace NorthPole.Tests;

public class TaxCalculatorTests
{
    private readonly TaxCalculator _calculator = new();

    [Theory]
    [InlineData(Region.NorthPole, 0)]      // 0% tax
    [InlineData(Region.Nordic, 1500)]       // 15% of 10000
    [InlineData(Region.Alpine, 2000)]       // 20% of 10000
    [InlineData(Region.Arctic, 1000)]       // 10% of 10000
    public void CalculateTaxFor_ReturnsCorrectAmount(Region region, int expectedTaxCents)
    {
        var company = new ElfCompany("Test Company", "standard", region);
        var cost = Money.FromCents(10000); // $100

        var result = _calculator.CalculateTaxFor(cost, company);

        Assert.Equal(Money.FromCents(expectedTaxCents), result.Amount);
    }

    [Theory]
    [InlineData(Region.NorthPole, "Tax (North Pole - 0%): $0.00")]
    [InlineData(Region.Nordic, "Tax (Nordic Region - 15%): $15.00")]
    [InlineData(Region.Alpine, "Tax (Alpine Region - 20%): $20.00")]
    [InlineData(Region.Arctic, "Tax (Arctic Region - 10%): $10.00")]
    public void CalculateTaxFor_ReturnsCorrectDescription(Region region, string expectedDescription)
    {
        var company = new ElfCompany("Test Company", "standard", region);
        var cost = Money.FromCents(10000); // $100

        var result = _calculator.CalculateTaxFor(cost, company);

        Assert.Equal(expectedDescription, result.ToString());
    }
}
