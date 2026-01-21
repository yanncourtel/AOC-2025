using NorthPole.Domain;

namespace NorthPole.Calculation;

public static class EnrichedInvoiceExtensions
{
    extension(EnrichedInvoice invoice)
    {
        public CalculatedInvoice CalculateWithoutTaxes(InvoiceCalculator calculator)
            => calculator.Calculate(invoice, false);

        public CalculatedInvoice CalculateWithTaxes(InvoiceCalculator calculator)
            => calculator.Calculate(invoice, true);
    }
}
