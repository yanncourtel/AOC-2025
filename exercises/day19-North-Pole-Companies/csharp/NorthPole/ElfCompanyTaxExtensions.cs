namespace NorthPole;

public static class ElfCompanyTaxExtensions
{
    private static readonly ITaxCalculator TaxCalculator = new TaxCalculator();

    public static Tax CalculateTax(this ElfCompany company, Money cost)
    {
        return TaxCalculator.CalculateTaxFor(cost, company);
    }
}