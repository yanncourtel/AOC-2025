// dependencies.ts
import { Child, Gift } from "./models";

export interface WishList {
  identifyGift(child: Child): Gift | null;
}

export interface Factory {
  findManufacturedGift(gift: Gift): Gift | null;
}

export interface Inventory {
  pickUpGift(barCode: string): Gift | null;
}
