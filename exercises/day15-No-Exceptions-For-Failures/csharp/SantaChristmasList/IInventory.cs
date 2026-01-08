namespace SantaChristmasList;

public interface IInventory
{
    Gift? PickUpGift(string barCode);
}