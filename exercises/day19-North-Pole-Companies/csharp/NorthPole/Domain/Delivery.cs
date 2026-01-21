namespace NorthPole.Domain;

public class Delivery(string companyId, int packages)
{
    public string CompanyID { get; } = companyId;
    public int Packages { get; } = packages;
}