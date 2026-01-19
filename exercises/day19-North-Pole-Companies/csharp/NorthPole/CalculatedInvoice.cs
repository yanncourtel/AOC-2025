using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public class CalculatedInvoice
{
    public string Customer { get; }
    public List<InvoiceLine> Lines { get; }
    public Money Subtotal { get; }
    public Money TotalTax { get; }
    public Money TotalAmount { get; }
    public int TotalLoyaltyPoints { get; }

    private CalculatedInvoice(
        string customer,
        List<InvoiceLine> lines,
        Money subtotal,
        Money totalTax,
        Money totalAmount,
        int totalLoyaltyPoints)
    {
        Customer = customer;
        Lines = lines;
        Subtotal = subtotal;
        TotalTax = totalTax;
        TotalAmount = totalAmount;
        TotalLoyaltyPoints = totalLoyaltyPoints;
    }

    // Factory method for cleaner construction
    public static CalculatedInvoice From(string customer, List<InvoiceLine> lines)
    {
        var subtotal = lines.Aggregate(Money.Zero, (sum, line) => sum + line.Cost);
        var totalTax = lines
            .Where(line => line.Tax.HasValue)
            .Aggregate(Money.Zero, (sum, line) => sum + line.Tax!.Value.Amount);
        var totalAmount = subtotal + totalTax;
        var totalLoyaltyPoints = lines.Sum(line => line.LoyaltyPoints);

        return new CalculatedInvoice(
            customer,
            lines,
            subtotal,
            totalTax,
            totalAmount,
            totalLoyaltyPoints
        );
    }
}