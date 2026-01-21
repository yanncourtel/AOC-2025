using System.Collections.Generic;

namespace NorthPole.Domain;

public class Invoice(string customer, List<Delivery> deliveries)
{
    public string Customer { get; } = customer;
    public List<Delivery> Deliveries { get; } = deliveries;
}