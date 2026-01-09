using System.Collections.Generic;

namespace NorthPole;

public class EnrichedInvoice
{
    public string Customer { get; }
    public List<EnrichedDelivery> Deliveries { get; }

    public EnrichedInvoice(string customer, List<EnrichedDelivery> deliveries)
    {
        Customer = customer;
        Deliveries = deliveries;
    }
}