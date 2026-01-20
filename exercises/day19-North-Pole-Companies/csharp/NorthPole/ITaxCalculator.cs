namespace NorthPole;

public interface ITaxCalculator
{
    Tax CalculateTaxFor(Money cost, ElfCompany company);
}