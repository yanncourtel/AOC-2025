namespace NorthPole;

public readonly struct Tax
{
    public Money Amount { get; }
    
    private string RegionName { get; }
    private double Rate { get; }

    public Tax(string regionName, double rate, Money amount)
    {
        RegionName = regionName;
        Rate = rate;
        Amount = amount;
    }

    public override string ToString()
    {
        return $"Tax ({RegionName} - {Rate:P0}): {Amount}";
    }

    public static Tax? NoTax => null;

}