using System.Collections.Generic;
using System.Linq;

namespace NorthPole;

public static class InvoiceExtensions
{
    extension(Invoice invoice)
    {
        public EnrichedInvoice EnrichWith(Dictionary<string, ElfCompany> companies)
            => new(
                invoice.Customer,
                invoice.Deliveries
                    .Select(d => new EnrichedDelivery(d, companies[d.CompanyID]))
                    .ToList());
    }
}
