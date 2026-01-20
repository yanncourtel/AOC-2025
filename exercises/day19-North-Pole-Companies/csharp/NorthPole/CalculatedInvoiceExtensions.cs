namespace NorthPole;

public static class CalculatedInvoiceExtensions
{
    private static readonly IInvoiceFormatter TextInvoiceFormatter = new TextInvoiceFormatter();
    private static readonly IInvoiceFormatter TextInvoiceWithTaxFormatter = new TextInvoiceWithTaxFormatter();

    public static string Print(this CalculatedInvoice invoice) 
        => TextInvoiceFormatter.Format(invoice);

    public static string PrintWithTaxes(this CalculatedInvoice invoice) 
        => TextInvoiceWithTaxFormatter.Format(invoice);
}