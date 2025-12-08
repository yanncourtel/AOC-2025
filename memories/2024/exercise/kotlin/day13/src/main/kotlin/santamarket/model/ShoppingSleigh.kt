package santamarket.model

class ShoppingSleigh {
    private val items = mutableListOf<ProductQuantity>()

    fun addItemQuantity(product: Product, quantity: Double) {
        items.add(ProductQuantity(product, quantity))
    }

    fun getItems(): List<ProductQuantity> = items.toList()

    fun handleOffers(receipt: Receipt, offers: Map<Product, Offer>, catalog: SantamarketCatalog) {
        val productQuantities = mutableMapOf<Product, Double>()
        for (item in items) {
            val p = item.product
            if (productQuantities.containsKey(p)) {
                productQuantities[p] = productQuantities[p]!! + item.quantity
            } else {
                productQuantities[p] = item.quantity
            }
        }

        for ((p, quantity) in productQuantities) {
            if (offers.containsKey(p)) {
                val offer = offers[p]!!
                val unitPrice = catalog.getUnitPrice(p)
                val quantityAsInt = quantity.toInt()
                var discountAmount = 0.0

                when (offer.offerType) {
                    SpecialOfferType.THREE_FOR_TWO -> {
                        if (quantityAsInt > 2) {
                            val numberOfSets = quantityAsInt / 3
                            discountAmount = numberOfSets * unitPrice
                        }
                    }
                    SpecialOfferType.TEN_PERCENT_DISCOUNT -> {
                        discountAmount = quantity * unitPrice * 0.1
                    }
                }

                if (discountAmount > 0) {
                    receipt.addDiscount(discountAmount)
                }
            }
        }
    }
}
