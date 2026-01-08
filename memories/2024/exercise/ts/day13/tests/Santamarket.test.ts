import {FakeCatalog} from "./FakeCatalog";
import {Product} from "../src/santamarket.model/Product";
import {ProductUnit} from "../src/santamarket.model/ProductUnit";
import {ShoppingSleigh} from "../src/santamarket.model/ShoppingSleigh";
import {ChristmasElf} from "../src/santamarket.model/ChristmasElf";
import {SpecialOfferType} from "../src/santamarket.model/SpecialOfferType";

describe('Santamarket', () => {
    it('should calculate total with no discount', () => {
        const catalog = new FakeCatalog();
        const teddyBear = new Product('teddyBear', ProductUnit.EACH);
        catalog.addProduct(teddyBear, 1.0);

        const sleigh = new ShoppingSleigh();
        sleigh.addItemQuantity(teddyBear, 3);

        const elf = new ChristmasElf(catalog);
        const receipt = elf.checksOutArticlesFrom(sleigh);

        expect(receipt.getTotalPrice()).toBeCloseTo(3.0, 2);
    });

    it('should apply ten percent discount', () => {
        const catalog = new FakeCatalog();
        const turkey = new Product('turkey', ProductUnit.KILO);
        catalog.addProduct(turkey, 2.0);

        const sleigh = new ShoppingSleigh();
        sleigh.addItemQuantity(turkey, 2.0);

        const elf = new ChristmasElf(catalog);
        elf.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, turkey, 10.0);
        const receipt = elf.checksOutArticlesFrom(sleigh);

        expect(receipt.getTotalPrice()).toBeCloseTo(3.6, 2); // 4.0 - 0.4
    });

    it('should apply three for two discount', () => {
        const catalog = new FakeCatalog();
        const teddyBear = new Product('teddyBear', ProductUnit.EACH);
        catalog.addProduct(teddyBear, 1.0);

        const elf = new ChristmasElf(catalog);
        elf.addSpecialOffer(SpecialOfferType.THREE_FOR_TWO, teddyBear, 0.0);

        const sleigh = new ShoppingSleigh();
        sleigh.addItemQuantity(teddyBear, 3);

        const receipt = elf.checksOutArticlesFrom(sleigh);

        expect(receipt.getTotalPrice()).toBeCloseTo(2.0, 2); // Buy 3, pay for 2
    });
});
