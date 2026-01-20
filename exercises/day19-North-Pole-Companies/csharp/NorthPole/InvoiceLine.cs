namespace NorthPole;

public class InvoiceLine
{
    public string CompanyName { get; }
    public int Packages { get; }
    public Money Cost { get; }
    public Tax? Tax { get; }  // Nullable for non-tax invoices
    public int LoyaltyPoints { get; }

    public static InvoiceLine LineFor(EnrichedDelivery enriched)
    {
        var cost = enriched.CalculateCost();
        var loyaltyPoints = enriched.CalculateLoyaltyPoints();

        return new InvoiceLine(
            enriched.Company.Name,
            enriched.Packages,
            cost,
            null,
            loyaltyPoints
        );
    }

    public static InvoiceLine LineWithTaxFor(EnrichedDelivery enriched)
    {
        var cost = enriched.CalculateCost();
        var tax = enriched.CalculateTax(cost);
        var loyaltyPoints = enriched.CalculateLoyaltyPoints();

        return new InvoiceLine(
            enriched.Company.Name,
            enriched.Packages,
            cost,
            tax,
            loyaltyPoints
        );
    }

    private InvoiceLine(string companyName, int packages, Money cost, Tax? tax, int loyaltyPoints)
    {
        CompanyName = companyName;
        Packages = packages;
        Cost = cost;
        Tax = tax;
        LoyaltyPoints = loyaltyPoints;
    }

    public Money GetTaxAmount() => Tax?.Amount ?? Money.Zero;

    public bool HasTax() => Tax.HasValue;
}