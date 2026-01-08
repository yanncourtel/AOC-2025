// Business.ts
import { Factory, Inventory, WishList } from "./dependencies";
import { Child, Gift, Sleigh } from "./models";

export class Business {
  constructor(
    private readonly factory: Factory,
    private readonly inventory: Inventory,
    private readonly wishList: WishList
  ) {}

  loadGiftsInSleigh(...children: Child[]): Sleigh {
    const sleigh = new Sleigh();

    for (const child of children) {
      try {
        this.loadGiftForChild(sleigh, child);
      } catch (e) {
        if (
          e instanceof ChildWishNotFoundError ||
          e instanceof GiftNotManufacturedError ||
          e instanceof GiftOutOfStockError
        ) {
          throw new BusinessError("Unexpected error while loading sleigh", e as Error);
        }
        throw e;
      }
    }

    return sleigh;
  }

  private loadGiftForChild(sleigh: Sleigh, child: Child): void {
    const gift = this.wishList.identifyGift(child);
    if (!gift) {
      throw new ChildWishNotFoundError(child);
    }

    const manufacturedGift = this.factory.findManufacturedGift(gift);
    if (!manufacturedGift) {
      throw new GiftNotManufacturedError(gift);
    }

    const finalGift = this.inventory.pickUpGift(manufacturedGift.barCode);
    if (!finalGift) {
      throw new GiftOutOfStockError(manufacturedGift);
    }

    sleigh.put(child, `Gift: ${finalGift.name} has been loaded!`);
  }
}

export class ChildWishNotFoundError extends Error {
  constructor(child: Child) {
    super(`No wish found for child: ${child.name}`);
    this.name = "ChildWishNotFoundError";
  }
}

export class GiftNotManufacturedError extends Error {
  constructor(gift: Gift) {
    super(`Gift has not been manufactured: ${gift.name}`);
    this.name = "GiftNotManufacturedError";
  }
}

export class GiftOutOfStockError extends Error {
  constructor(gift: Gift) {
    super(`Gift out of stock: ${gift.name}`);
    this.name = "GiftOutOfStockError";
  }
}

export class BusinessError extends Error {
  constructor(message: string, public readonly cause: Error) {
    super(message);
    this.name = "BusinessError";
  }
}
