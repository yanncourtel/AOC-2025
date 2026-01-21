namespace NorthPole;

public class EnrichedDelivery(Delivery delivery, ElfCompany company)
{
    public int Packages { get; } = delivery.Packages;
    public ElfCompany Company { get; } = company;
}
