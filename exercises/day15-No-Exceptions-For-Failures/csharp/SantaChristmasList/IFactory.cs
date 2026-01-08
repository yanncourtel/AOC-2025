namespace SantaChristmasList;

public interface IFactory
{
    Gift? FindManufacturedGift(Gift gift);
}