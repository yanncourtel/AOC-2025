namespace NorthPole;

public readonly struct Tax
{
    public string RegionName { get; }
    public double Rate { get; }
    public Money Amount { get; }

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

    public static readonly Tax Zero = new("None", 0.0, Money.Zero);
}