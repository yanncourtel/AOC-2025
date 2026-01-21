namespace NorthPole;

public readonly struct Tax(string regionName, double rate, Money amount)
{
    public Money Amount { get; } = amount;

    private string RegionName { get; } = regionName;
    private double Rate { get; } = rate;

    public override string ToString()
    {
        return $"Tax ({RegionName} - {Rate:P0}): {Amount}";
    }

    public static Tax? NoTax => null;

}