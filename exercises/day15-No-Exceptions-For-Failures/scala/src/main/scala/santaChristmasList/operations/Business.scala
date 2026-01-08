package santaChristmasList.operations

import santaChristmasList.operations.dependencies._
import santaChristmasList.operations.models._

class Business(factory: Factory, inventory: Inventory, wishList: WishList) {

  def loadGiftsInSleigh(children: Child*): Sleigh = {
    val sleigh = new Sleigh

    children.foreach { child =>
      try {
        loadGiftForChild(sleigh, child)
      } catch {
        case e @ (_: ChildWishNotFoundException
                | _: GiftNotManufacturedException
                | _: GiftOutOfStockException) =>
          throw new BusinessException("Unexpected error while loading sleigh", e)
      }
    }

    sleigh
  }

  private def loadGiftForChild(sleigh: Sleigh, child: Child): Unit = {
    val gift = Option(wishList.identifyGift(child))
      .getOrElse(throw new ChildWishNotFoundException(child))

    val manufacturedGift = Option(factory.findManufacturedGift(gift))
      .getOrElse(throw new GiftNotManufacturedException(gift))

    val finalGift = Option(inventory.pickUpGift(manufacturedGift.barCode))
      .getOrElse(throw new GiftOutOfStockException(manufacturedGift))

    sleigh.put(child, s"Gift: ${finalGift.name} has been loaded!")
  }
}

class ChildWishNotFoundException(child: Child)
  extends RuntimeException(s"No wish found for child: ${child.name}")

class GiftNotManufacturedException(gift: Gift)
  extends RuntimeException(s"Gift has not been manufactured: ${gift.name}")

class GiftOutOfStockException(gift: Gift)
  extends RuntimeException(s"Gift out of stock: ${gift.name}")

class BusinessException(message: String, cause: Throwable)
  extends RuntimeException(message, cause)
