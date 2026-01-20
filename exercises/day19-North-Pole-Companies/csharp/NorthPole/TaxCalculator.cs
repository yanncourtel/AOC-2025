using System;
using System.Collections.Generic;

namespace NorthPole;

public interface ITaxCalculator
{
    Tax CalculateTax(Money cost, string region);
}

public class TaxCalculator : ITaxCalculator
{
    private static readonly Dictionary<string, (string Name, double Rate)> TaxRates = new()
    {
        ["north-pole"] = ("North Pole", 0.0),
        ["nordic"] = ("Nordic Region", 0.15),
        ["alpine"] = ("Alpine Region", 0.20),
        ["arctic"] = ("Arctic Region", 0.10)
    };

    public Tax CalculateTax(Money cost, string region)
    {
        var taxInfo = GetTaxInfo(region);
        var taxAmount = cost * taxInfo.Rate;
        return new Tax(taxInfo.Name, taxInfo.Rate, taxAmount);
    }

    private static (string Name, double Rate) GetTaxInfo(string region)
    {
        if (!TaxRates.TryGetValue(region, out var taxInfo))
        {
            throw new Exception($"Unknown region: {region}");
        }
        return taxInfo;
    }
}