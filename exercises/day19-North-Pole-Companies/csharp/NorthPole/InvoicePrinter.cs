using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public class InvoicePrinter
{
    // Legacy methods - kept for backward compatibility
    public string Print(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => EnrichInvoice(invoice, elfCompanies)
            .Print();

    public string PrintWithTaxes(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies)
        => EnrichInvoice(invoice, elfCompanies)
            .PrintWithTaxes();

    private static EnrichedInvoice EnrichInvoice(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies) 
        => new(
            invoice.Customer, 
            invoice.Deliveries
                .Select(delivery => new EnrichedDelivery(delivery, elfCompanies[delivery.CompanyID]))
                .ToList());
}