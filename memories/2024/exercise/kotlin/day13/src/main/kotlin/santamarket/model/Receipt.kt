package santamarket.model

class Receipt {
    private var totalPrice = 0.0
    private var totalDiscount = 0.0

    fun addProduct(price: Double) {
        totalPrice += price
    }

    fun addDiscount(discount: Double) {
        totalDiscount += discount
    }

    fun getTotalPrice(): Double = totalPrice - totalDiscount
}
