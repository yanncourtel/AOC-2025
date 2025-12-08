package santamarket.model

class Receipt {
  private var totalPrice: Double = 0.0
  private var totalDiscount: Double = 0.0

  def addProduct(price: Double): Unit = {
    totalPrice += price
  }

  def addDiscount(discount: Double): Unit = {
    totalDiscount += discount
  }

  def getTotalPrice: Double = totalPrice - totalDiscount
}
