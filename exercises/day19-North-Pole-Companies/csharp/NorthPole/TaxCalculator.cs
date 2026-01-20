using System;
using System.Collections.Generic;

namespace NorthPole;

public class TaxCalculator : ITaxCalculator
{
    private static readonly Dictionary<string, (string Name, double Rate)> TaxRates = new()
    {
        ["north-pole"] = ("North Pole", 0.0),
        ["nordic"] = ("Nordic Region", 0.15),
        ["alpine"] = ("Alpine Region", 0.20),
        ["arctic"] = ("Arctic Region", 0.10)
    };

    public Tax CalculateTaxFor(Money cost, ElfCompany company)
    {
        if (!TaxRates.TryGetValue(company.Region, out var taxInfo))
        {
            throw new Exception($"Unknown region: {company.Region}");
        }

        var taxAmount = cost * taxInfo.Rate;
        return new Tax(taxInfo.Name, taxInfo.Rate, taxAmount);
    }
}