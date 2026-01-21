using NorthPole.Domain;

namespace NorthPole.Calculation;

public interface ITaxCalculator
{
    Tax CalculateTaxFor(Money cost, ElfCompany company);
}