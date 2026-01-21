using NorthPole.Calculation;

namespace NorthPole.Formatting;

public interface IInvoiceFormatter
{
    string Format(CalculatedInvoice invoice);
}