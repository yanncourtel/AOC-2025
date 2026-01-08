package santamarket.model

class ChristmasElf(private val catalog: SantamarketCatalog) {
    private val offers = mutableMapOf<Product, Offer>()

    fun addSpecialOffer(offerType: SpecialOfferType, product: Product, argument: Double) {
        offers[product] = Offer(offerType, product, argument)
    }

    fun checksOutArticlesFrom(sleigh: ShoppingSleigh): Receipt {
        val receipt = Receipt()

        for (item in sleigh.getItems()) {
            val p = item.product
            val quantity = item.quantity
            val unitPrice = catalog.getUnitPrice(p)
            receipt.addProduct(quantity * unitPrice)
        }

        sleigh.handleOffers(receipt, offers, catalog)

        return receipt
    }
}
