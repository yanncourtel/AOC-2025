package santaChristmasList.operations;

import santaChristmasList.operations.dependencies.Factory;
import santaChristmasList.operations.dependencies.Inventory;
import santaChristmasList.operations.dependencies.WishList;
import santaChristmasList.operations.models.Child;
import santaChristmasList.operations.models.Gift;
import santaChristmasList.operations.models.Sleigh;

public class Business {

    private final Factory factory;
    private final Inventory inventory;
    private final WishList wishList;

    public Business(Factory factory, Inventory inventory, WishList wishList) {
        this.factory = factory;
        this.inventory = inventory;
        this.wishList = wishList;
    }

    public Sleigh loadGiftsInSleigh(Child... children) {
        Sleigh sleigh = new Sleigh();

        for (Child child : children) {
            try {
                loadGiftForChild(sleigh, child);
            } catch (ChildWishNotFoundException
                     | GiftNotManufacturedException
                     | GiftOutOfStockException e) {
                throw new BusinessException("Unexpected error while loading sleigh", e);
            }
        }

        return sleigh;
    }

    private void loadGiftForChild(Sleigh sleigh, Child child) {
        Gift gift = wishList.identifyGift(child);
        if (gift == null) {
            throw new ChildWishNotFoundException(child);
        }

        Gift manufacturedGift = factory.findManufacturedGift(gift);
        if (manufacturedGift == null) {
            throw new GiftNotManufacturedException(gift);
        }

        Gift finalGift = inventory.pickUpGift(manufacturedGift.barCode());
        if (finalGift == null) {
            throw new GiftOutOfStockException(manufacturedGift);
        }

        sleigh.put(child, "Gift: " + finalGift.name() + " has been loaded!");
    }

    public static class ChildWishNotFoundException extends RuntimeException {
        public ChildWishNotFoundException(Child child) {
            super("No wish found for child: " + child.name());
        }
    }

    public static class GiftNotManufacturedException extends RuntimeException {
        public GiftNotManufacturedException(Gift gift) {
            super("Gift has not been manufactured: " + gift.name());
        }
    }

    public static class GiftOutOfStockException extends RuntimeException {
        public GiftOutOfStockException(Gift gift) {
            super("Gift out of stock: " + gift.name());
        }
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
