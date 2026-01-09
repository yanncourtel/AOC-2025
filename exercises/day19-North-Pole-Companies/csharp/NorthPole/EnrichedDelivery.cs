namespace NorthPole;

public class EnrichedDelivery
{
    public int Packages { get; }
    public ElfCompany Company { get; }

    public EnrichedDelivery(Delivery delivery, ElfCompany company)
    {
        Packages = delivery.Packages;
        Company = company;
    }
}