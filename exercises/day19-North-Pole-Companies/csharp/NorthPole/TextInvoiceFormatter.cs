using System.Text;

namespace NorthPole;

public class TextInvoiceFormatter : IInvoiceFormatter
{
    public string Format(CalculatedInvoice invoice)
    {
        var result = new StringBuilder($"Invoice for {invoice.Customer}\n");

        foreach (var line in invoice.Lines)
        {
            result.AppendLine($" {line.CompanyName}: {line.Cost} ({line.Packages} packages)");
        }

        result.AppendLine($"Amount owed is {invoice.TotalAmount}");
        result.AppendLine($"You earned {invoice.TotalLoyaltyPoints} loyalty points");
        
        return result.ToString();
    }
}