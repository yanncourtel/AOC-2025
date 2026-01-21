using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public class InvoicePrinter
{
    private readonly InvoiceCalculator _calculator;
    private readonly IInvoiceFormatter _formatter;
    private readonly IInvoiceFormatter _taxFormatter;

    public InvoicePrinter() : this(
        new InvoiceCalculator(
            new Dictionary<string, IDeliveryPricer>
            {
                ["express"] = new ExpressDeliveryPricer(),
                ["standard"] = new StandardDeliveryPricer()
            },
            new TaxCalculator(),
            new LoyaltyCalculator()),
        new TextInvoiceFormatter(),
        new TextInvoiceWithTaxFormatter())
    {
    }

    public InvoicePrinter(
        InvoiceCalculator calculator,
        IInvoiceFormatter formatter,
        IInvoiceFormatter taxFormatter)
    {
        _calculator = calculator;
        _formatter = formatter;
        _taxFormatter = taxFormatter;
    }

    public string Print(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => Print(
            EnrichInvoice(invoice, elfCompanies));

    private string Print(EnrichedInvoice enriched) 
        => _formatter.Format(
            _calculator.Calculate(enriched, includeTax: false));

    public string PrintWithTaxes(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => PrintWithTaxes(
            EnrichInvoice(invoice, elfCompanies));

    private string PrintWithTaxes(EnrichedInvoice enriched) 
        => _taxFormatter.Format(
            _calculator.Calculate(enriched, includeTax: true));

    private static EnrichedInvoice EnrichInvoice(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies)
        => new(
            invoice.Customer,
            invoice.Deliveries
                .Select(delivery => new EnrichedDelivery(delivery, elfCompanies[delivery.CompanyID]))
                .ToList());
}
