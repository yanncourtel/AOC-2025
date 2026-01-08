package santaChristmasList.operations.dependencies

import santaChristmasList.operations.models._

trait WishList {
  def identifyGift(child: Child): Gift
}

trait Factory {
  def findManufacturedGift(gift: Gift): Gift
}

trait Inventory {
  def pickUpGift(barCode: String): Gift
}
