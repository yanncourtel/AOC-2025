
using System.Globalization;

namespace NorthPole;

public readonly struct Money
{
    private static readonly CultureInfo CurrencyFormat = new("en-US");

    private int AmountInCents { get; }

    private Money(int amountInCents)
    {
        AmountInCents = amountInCents;
    }

    public static Money FromCents(int cents) => new(cents);

    public override string ToString()
    {
        return (AmountInCents / 100.0).ToString("C", CurrencyFormat);
    }

    public static Money operator +(Money left, Money right)
        => new(left.AmountInCents + right.AmountInCents);

    public static Money operator *(Money money, double multiplier)
        => new((int)(money.AmountInCents * multiplier));

    public static Money operator *(double multiplier, Money money)
        => new((int)(money.AmountInCents * multiplier));

    public static bool operator ==(Money left, Money right)
        => left.AmountInCents == right.AmountInCents;

    public static bool operator !=(Money left, Money right)
        => left.AmountInCents != right.AmountInCents;

    public override bool Equals(object obj)
        => obj is Money other && AmountInCents == other.AmountInCents;

    public override int GetHashCode()
        => AmountInCents.GetHashCode();

    // Useful constant
    public static readonly Money Zero = new(0);
}