using System.Collections.Generic;

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
        => invoice
            .EnrichWith(elfCompanies)
            .CalculateWithoutTaxes(calculator)
            .FormatWith(formatter);

    public string PrintWithTaxes(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies)
        => invoice
            .EnrichWith(elfCompanies)
            .CalculateWithTaxes(calculator)
            .FormatWith(taxFormatter);
}
