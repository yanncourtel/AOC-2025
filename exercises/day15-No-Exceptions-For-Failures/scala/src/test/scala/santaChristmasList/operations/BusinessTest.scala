package santaChristmasList.operations

import org.scalatest.funsuite.AnyFunSuite
import santaChristmasList.operations.dependencies._
import santaChristmasList.operations.models._

class BusinessTest extends AnyFunSuite {

  test("child wish not found error message is extracted") {
    val timmy = Child("Timmy")

    val wishList = new WishList {
      override def identifyGift(child: Child): Gift = null
    }
    val factory = new Factory {
      override def findManufacturedGift(gift: Gift): Gift = null
    }
    val inventory = new Inventory {
      override def pickUpGift(barCode: String): Gift = null
    }

    val business = new Business(factory, inventory, wishList)

    val msg = loadGiftAndExtractInnerErrorMessage(business, timmy)

    assert(msg == "No wish found for child: Timmy")
  }

  test("gift not manufactured error message is extracted") {
    val timmy = Child("Timmy")
    val wishedGift = Gift("Lego Death Star", "BARCODE-123")

    val wishList = new WishList {
      override def identifyGift(child: Child): Gift = wishedGift
    }
    val factory = new Factory {
      override def findManufacturedGift(gift: Gift): Gift = null
    }
    val inventory = new Inventory {
      override def pickUpGift(barCode: String): Gift = null
    }

    val business = new Business(factory, inventory, wishList)

    val msg = loadGiftAndExtractInnerErrorMessage(business, timmy)

    assert(msg == "Gift has not been manufactured: Lego Death Star")
  }

  test("gift out of stock error message is extracted") {
    val timmy = Child("Timmy")
    val wishedGift = Gift("Red Bike", "BARCODE-456")
    val manufacturedGift = Gift("Red Bike", "BARCODE-456")

    val wishList = new WishList {
      override def identifyGift(child: Child): Gift = wishedGift
    }
    val factory = new Factory {
      override def findManufacturedGift(gift: Gift): Gift = manufacturedGift
    }
    val inventory = new Inventory {
      override def pickUpGift(barCode: String): Gift = null
    }

    val business = new Business(factory, inventory, wishList)

    val msg = loadGiftAndExtractInnerErrorMessage(business, timmy)

    assert(msg == "Gift out of stock: Red Bike")
  }

  private def loadGiftAndExtractInnerErrorMessage(business: Business, child: Child): String = {
    val ex = intercept[BusinessException] {
      business.loadGiftsInSleigh(child)
    }
    val cause = ex.getCause
    assert(cause != null)
    cause.getMessage
  }
}
