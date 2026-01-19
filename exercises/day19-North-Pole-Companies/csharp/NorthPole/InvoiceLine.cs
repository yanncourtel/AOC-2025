namespace NorthPole;

public class InvoiceLine
{
    public string CompanyName { get; }
    public int Packages { get; }
    public Money Cost { get; }
    public Tax? Tax { get; }  // Nullable for non-tax invoices
    public int LoyaltyPoints { get; }

    public InvoiceLine(string companyName, int packages, Money cost, Tax? tax, int loyaltyPoints)
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