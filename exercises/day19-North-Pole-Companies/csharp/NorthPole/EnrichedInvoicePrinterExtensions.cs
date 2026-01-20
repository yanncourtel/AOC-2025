using System.Linq;

namespace NorthPole;

public static class EnrichedInvoicePrinterExtensions
{
    public static string Print(this EnrichedInvoice invoice) 
        => invoice.Calculate(false)
            .Print();

    public static string PrintWithTaxes(this EnrichedInvoice invoice) 
        => invoice.Calculate(true)
            .PrintWithTaxes();

    private static CalculatedInvoice Calculate(this EnrichedInvoice invoice, bool includeTax) 
        => CalculatedInvoice.From(invoice.Customer, invoice.Deliveries
            .Select(d => CalculateInvoiceLine(d, includeTax))
            .ToList());

    private static InvoiceLine CalculateInvoiceLine(EnrichedDelivery enriched, bool includeTax) 
        => includeTax
            ? InvoiceLine.LineWithTaxFor(enriched)
            : InvoiceLine.LineFor(enriched);
}