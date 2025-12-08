import { Product } from './Product';
import { ProductQuantity } from './ProductQuantity';
import { Receipt } from './Receipt';
import { Offer } from './Offer';
import { SantamarketCatalog } from './SantamarketCatalog';
import { SpecialOfferType } from './SpecialOfferType';

export class ShoppingSleigh {
    private items: ProductQuantity[] = [];

    addItemQuantity(product: Product, quantity: number): void {
        this.items.push(new ProductQuantity(product, quantity));
    }

    getItems(): ProductQuantity[] {
        return [...this.items];
    }

    handleOffers(receipt: Receipt, offers: Map<Product, Offer>, catalog: SantamarketCatalog): void {
        const productQuantities = new Map<Product, number>();
        for (const item of this.items) {
            const p = item.getProduct();
            if (productQuantities.has(p)) {
                productQuantities.set(p, productQuantities.get(p)! + item.getQuantity());
            } else {
                productQuantities.set(p, item.getQuantity());
            }
        }

        for (const [p, quantity] of productQuantities) {
            if (offers.has(p)) {
                const offer = offers.get(p)!;
                const unitPrice = catalog.getUnitPrice(p);
                const quantityAsInt = Math.floor(quantity);
                let discountAmount = 0;

                if (offer.offerType === SpecialOfferType.THREE_FOR_TWO) {
                    if (quantityAsInt > 2) {
                        const numberOfSets = Math.floor(quantityAsInt / 3);
                        discountAmount = numberOfSets * unitPrice;
                    }
                } else if (offer.offerType === SpecialOfferType.TEN_PERCENT_DISCOUNT) {
                    discountAmount = quantity * unitPrice * 0.1;
                }

                if (discountAmount > 0) {
                    receipt.addDiscount(discountAmount);
                }
            }
        }
    }
}
