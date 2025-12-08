package santamarket.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SantamarketTest {

    @Test
    void noDiscount() {
        SantamarketCatalog catalog = new FakeCatalog();
        Product teddyBear = new Product("teddyBear", ProductUnit.EACH);
        catalog.addProduct(teddyBear, 1.0);

        ShoppingSleigh sleigh = new ShoppingSleigh();
        sleigh.addItemQuantity(teddyBear, 3);

        ChristmasElf elf = new ChristmasElf(catalog);
        Receipt receipt = elf.checksOutArticlesFrom(sleigh);

        assertEquals(3.0, receipt.getTotalPrice(), 0.01);
    }

    @Test
    void tenPercentDiscount() {
        SantamarketCatalog catalog = new FakeCatalog();
        Product turkey = new Product("turkey", ProductUnit.KILO);
        catalog.addProduct(turkey, 2.0);

        ShoppingSleigh sleigh = new ShoppingSleigh();
        sleigh.addItemQuantity(turkey, 2.0);

        ChristmasElf elf = new ChristmasElf(catalog);
        elf.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, turkey, 10.0);
        Receipt receipt = elf.checksOutArticlesFrom(sleigh);

        assertEquals(3.6, receipt.getTotalPrice(), 0.01);  // 4.0 - 0.4
    }

    @Test
    void threeForTwoDiscount() {
        SantamarketCatalog catalog = new FakeCatalog();
        Product teddyBear = new Product("teddyBear", ProductUnit.EACH);
        catalog.addProduct(teddyBear, 1.0);

        ChristmasElf elf = new ChristmasElf(catalog);
        elf.addSpecialOffer(SpecialOfferType.THREE_FOR_TWO, teddyBear, 0.0);

        ShoppingSleigh sleigh = new ShoppingSleigh();
        sleigh.addItemQuantity(teddyBear, 3);

        Receipt receipt = elf.checksOutArticlesFrom(sleigh);

        assertEquals(2.0, receipt.getTotalPrice(), 0.01);  // Buy 3, pay for 2
    }
}
