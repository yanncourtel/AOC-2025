using NorthPole.Domain;

namespace NorthPole.Calculation;

public class TaxCalculator : ITaxCalculator
{
    public Tax CalculateTaxFor(Money cost, ElfCompany company)
    {
        var (name, rate) = GetTaxInfo(company.Region);
        var taxAmount = cost * rate;
        return new Tax(name, rate, taxAmount);
    }

    private static (string Name, double Rate) GetTaxInfo(Region region) => region switch
    {
        Region.NorthPole => ("North Pole", 0.0),
        Region.Nordic => ("Nordic Region", 0.15),
        Region.Alpine => ("Alpine Region", 0.20),
        Region.Arctic => ("Arctic Region", 0.10),
        _ => throw new System.ArgumentOutOfRangeException(nameof(region), region, "Unknown region")
    };
}