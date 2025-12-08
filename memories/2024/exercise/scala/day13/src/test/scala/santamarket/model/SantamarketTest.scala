package santamarket.model

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SantamarketTest extends AnyFunSuite with Matchers {

  test("no discount") {
    val catalog = new FakeCatalog()
    val teddyBear = Product("teddyBear", ProductUnit.Each)
    catalog.addProduct(teddyBear, 1.0)

    val sleigh = new ShoppingSleigh()
    sleigh.addItemQuantity(teddyBear, 3.0)

    val elf = new ChristmasElf(catalog)
    val receipt = elf.checksOutArticlesFrom(sleigh)

    receipt.getTotalPrice should equal(3.0 +- 0.01)
  }

  test("ten percent discount") {
    val catalog = new FakeCatalog()
    val turkey = Product("turkey", ProductUnit.Kilo)
    catalog.addProduct(turkey, 2.0)

    val sleigh = new ShoppingSleigh()
    sleigh.addItemQuantity(turkey, 2.0)

    val elf = new ChristmasElf(catalog)
    elf.addSpecialOffer(SpecialOfferType.TenPercentDiscount, turkey, 10.0)
    val receipt = elf.checksOutArticlesFrom(sleigh)

    receipt.getTotalPrice should equal(3.6 +- 0.01) // 4.0 - 0.4
  }

  test("three for two discount") {
    val catalog = new FakeCatalog()
    val teddyBear = Product("teddyBear", ProductUnit.Each)
    catalog.addProduct(teddyBear, 1.0)

    val elf = new ChristmasElf(catalog)
    elf.addSpecialOffer(SpecialOfferType.ThreeForTwo, teddyBear, 0.0)

    val sleigh = new ShoppingSleigh()
    sleigh.addItemQuantity(teddyBear, 3.0)

    val receipt = elf.checksOutArticlesFrom(sleigh)

    receipt.getTotalPrice should equal(2.0 +- 0.01) // Buy 3, pay for 2
  }
}
