namespace NorthPole;

public interface IInvoiceFormatter
{
    string Format(CalculatedInvoice invoice);
}