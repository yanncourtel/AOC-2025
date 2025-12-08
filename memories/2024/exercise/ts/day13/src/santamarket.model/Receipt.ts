export class Receipt {
    private totalPrice: number = 0.0;
    private totalDiscount: number = 0.0;

    addProduct(price: number): void {
        this.totalPrice += price;
    }

    addDiscount(discount: number): void {
        this.totalDiscount += discount;
    }

    getTotalPrice(): number {
        return this.totalPrice - this.totalDiscount;
    }
}
