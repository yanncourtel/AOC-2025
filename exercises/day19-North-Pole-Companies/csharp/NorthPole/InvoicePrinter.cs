using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public class InvoicePrinter
{
    private readonly IInvoiceFormatter _formatter;
    private readonly IInvoiceFormatter _formatterWithTax;

    public InvoicePrinter() 
        : this(
            new TextInvoiceFormatter(), 
            new TextInvoiceWithTaxFormatter())
    {
    }

    private InvoicePrinter(
        IInvoiceFormatter formatter, 
        IInvoiceFormatter formatterWithTax)
    {
        _formatter = formatter;
        _formatterWithTax = formatterWithTax;
    }
    
    // Legacy methods - kept for backward compatibility
    public string Print(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => Print(
            EnrichInvoice(invoice, elfCompanies));

    private string Print(EnrichedInvoice invoice) 
        => _formatter.Format(
            CalculateInvoice(invoice, includeTax: false));

    public string PrintWithTaxes(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => PrintWithTaxes(
            EnrichInvoice(invoice, elfCompanies));

    private string PrintWithTaxes(EnrichedInvoice invoice) 
        => _formatterWithTax.Format(
            CalculateInvoice(invoice, includeTax: true));

    private CalculatedInvoice CalculateInvoice(EnrichedInvoice invoice, bool includeTax) 
        => CalculatedInvoice.From(invoice.Customer, invoice.Deliveries
            .Select(d => CalculateInvoiceLine(d, includeTax))
            .ToList());

    private InvoiceLine CalculateInvoiceLine(EnrichedDelivery enriched, bool includeTax) 
        => includeTax
            ? InvoiceLine.LineWithTaxFor(enriched)
            : InvoiceLine.LineFor(enriched);

    private static EnrichedInvoice EnrichInvoice(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => new(
            invoice.Customer, 
            invoice.Deliveries
                .Select(delivery => new EnrichedDelivery(delivery, elfCompanies[delivery.CompanyID]))
                .ToList());
}