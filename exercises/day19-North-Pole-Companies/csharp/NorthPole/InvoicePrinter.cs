using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public class InvoicePrinter(
    InvoiceCalculator calculator,
    IInvoiceFormatter formatter,
    IInvoiceFormatter taxFormatter)
{
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

    public string Print(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => Print(
            EnrichInvoice(invoice, elfCompanies));

    private string Print(EnrichedInvoice enriched) 
        => formatter.Format(
            calculator.Calculate(enriched, includeTax: false));

    public string PrintWithTaxes(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => PrintWithTaxes(
            EnrichInvoice(invoice, elfCompanies));

    private string PrintWithTaxes(EnrichedInvoice enriched) 
        => taxFormatter.Format(
            calculator.Calculate(enriched, includeTax: true));

    private static EnrichedInvoice EnrichInvoice(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies)
        => new(
            invoice.Customer,
            invoice.Deliveries
                .Select(delivery => new EnrichedDelivery(delivery, elfCompanies[delivery.CompanyID]))
                .ToList());
}
