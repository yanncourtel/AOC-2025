// Business.test.ts
import { Business, BusinessError } from "./Business";
import { Child, Gift } from "./models";
import { Factory, Inventory, WishList } from "./dependencies";

function loadGiftAndExtractInnerErrorMessage(business: Business, child: Child): string {
  try {
    business.loadGiftsInSleigh(child);
  } catch (e) {
    if (e instanceof BusinessError && e.cause) {
      return e.cause.message;
    }
    throw e;
  }
  throw new Error("Expected BusinessError to be thrown");
}

test("child wish not found error message is extracted", () => {
  const timmy = new Child("Timmy");

  const wishList: WishList = {
    identifyGift: () => null,
  };
  const factory: Factory = {
    findManufacturedGift: () => null,
  };
  const inventory: Inventory = {
    pickUpGift: () => null,
  };

  const business = new Business(factory, inventory, wishList);

  const message = loadGiftAndExtractInnerErrorMessage(business, timmy);

  expect(message).toBe("No wish found for child: Timmy");
});

test("gift not manufactured error message is extracted", () => {
  const timmy = new Child("Timmy");
  const wishedGift = new Gift("Lego Death Star", "BARCODE-123");

  const wishList: WishList = {
    identifyGift: () => wishedGift,
  };
  const factory: Factory = {
    findManufacturedGift: () => null,
  };
  const inventory: Inventory = {
    pickUpGift: () => null,
  };

  const business = new Business(factory, inventory, wishList);

  const message = loadGiftAndExtractInnerErrorMessage(business, timmy);

  expect(message).toBe("Gift has not been manufactured: Lego Death Star");
});

test("gift out of stock error message is extracted", () => {
  const timmy = new Child("Timmy");
  const wishedGift = new Gift("Red Bike", "BARCODE-456");
  const manufacturedGift = new Gift("Red Bike", "BARCODE-456");

  const wishList: WishList = {
    identifyGift: () => wishedGift,
  };
  const factory: Factory = {
    findManufacturedGift: () => manufacturedGift,
  };
  const inventory: Inventory = {
    pickUpGift: () => null,
  };

  const business = new Business(factory, inventory, wishList);

  const message = loadGiftAndExtractInnerErrorMessage(business, timmy);

  expect(message).toBe("Gift out of stock: Red Bike");
});
