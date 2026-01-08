package santamarket.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SantamarketTest {

    @Test
    fun `no discount`() {
        val catalog = FakeCatalog()
        val teddyBear = Product("teddyBear", ProductUnit.EACH)
        catalog.addProduct(teddyBear, 1.0)

        val sleigh = ShoppingSleigh()
        sleigh.addItemQuantity(teddyBear, 3.0)

        val elf = ChristmasElf(catalog)
        val receipt = elf.checksOutArticlesFrom(sleigh)

        assertEquals(3.0, receipt.getTotalPrice(), 0.01)
    }

    @Test
    fun `ten percent discount`() {
        val catalog = FakeCatalog()
        val turkey = Product("turkey", ProductUnit.KILO)
        catalog.addProduct(turkey, 2.0)

        val sleigh = ShoppingSleigh()
        sleigh.addItemQuantity(turkey, 2.0)

        val elf = ChristmasElf(catalog)
        elf.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, turkey, 10.0)
        val receipt = elf.checksOutArticlesFrom(sleigh)

        assertEquals(3.6, receipt.getTotalPrice(), 0.01) // 4.0 - 0.4
    }

    @Test
    fun `three for two discount`() {
        val catalog = FakeCatalog()
        val teddyBear = Product("teddyBear", ProductUnit.EACH)
        catalog.addProduct(teddyBear, 1.0)

        val elf = ChristmasElf(catalog)
        elf.addSpecialOffer(SpecialOfferType.THREE_FOR_TWO, teddyBear, 0.0)

        val sleigh = ShoppingSleigh()
        sleigh.addItemQuantity(teddyBear, 3.0)

        val receipt = elf.checksOutArticlesFrom(sleigh)

        assertEquals(2.0, receipt.getTotalPrice(), 0.01) // Buy 3, pay for 2
    }
}
