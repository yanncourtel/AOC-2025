namespace NorthPole;

public static class CalculatedInvoiceExtensions
{
    extension(CalculatedInvoice invoice)
    {
        public string FormatWith(IInvoiceFormatter formatter)
            => formatter.Format(invoice);
    }
}
