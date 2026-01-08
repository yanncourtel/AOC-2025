import { Product } from './Product';

export class ProductQuantity {
    constructor(
        private readonly product: Product,
        private readonly quantity: number
    ) {}

    getProduct(): Product {
        return this.product;
    }

    getQuantity(): number {
        return this.quantity;
    }
}
