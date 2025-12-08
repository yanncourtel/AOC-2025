import { SantamarketCatalog } from './SantamarketCatalog';
import { Product } from './Product';
import { Offer } from './Offer';
import { SpecialOfferType } from './SpecialOfferType';
import { ShoppingSleigh } from './ShoppingSleigh';
import { Receipt } from './Receipt';

export class ChristmasElf {
    private catalog: SantamarketCatalog;
    private offers: Map<Product, Offer> = new Map();

    constructor(catalog: SantamarketCatalog) {
        this.catalog = catalog;
    }

    addSpecialOffer(offerType: SpecialOfferType, product: Product, argument: number): void {
        this.offers.set(product, new Offer(offerType, product, argument));
    }

    checksOutArticlesFrom(sleigh: ShoppingSleigh): Receipt {
        const receipt = new Receipt();

        for (const item of sleigh.getItems()) {
            const p = item.getProduct();
            const quantity = item.getQuantity();
            const unitPrice = this.catalog.getUnitPrice(p);
            receipt.addProduct(quantity * unitPrice);
        }

        sleigh.handleOffers(receipt, this.offers, this.catalog);

        return receipt;
    }
}
