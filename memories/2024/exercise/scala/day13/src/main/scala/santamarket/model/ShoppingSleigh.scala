package santamarket.model

import scala.collection.mutable

class ShoppingSleigh {
  private val items = mutable.ListBuffer[ProductQuantity]()

  def addItemQuantity(product: Product, quantity: Double): Unit = {
    items += ProductQuantity(product, quantity)
  }

  def getItems: List[ProductQuantity] = items.toList

  def handleOffers(receipt: Receipt, offers: Map[Product, Offer], catalog: SantamarketCatalog): Unit = {
    val productQuantities = mutable.Map[Product, Double]()
    
    for (item <- items) {
      val p = item.product
      if (productQuantities.contains(p)) {
        productQuantities(p) = productQuantities(p) + item.quantity
      } else {
        productQuantities(p) = item.quantity
      }
    }

    for ((p, quantity) <- productQuantities) {
      if (offers.contains(p)) {
        val offer = offers(p)
        val unitPrice = catalog.getUnitPrice(p)
        val quantityAsInt = quantity.toInt
        var discountAmount = 0.0

        offer.offerType match {
          case SpecialOfferType.ThreeForTwo =>
            if (quantityAsInt > 2) {
              val numberOfSets = quantityAsInt / 3
              discountAmount = numberOfSets * unitPrice
            }
          case SpecialOfferType.TenPercentDiscount =>
            discountAmount = quantity * unitPrice * 0.1
          case _ => // Other offer types not handled
        }

        if (discountAmount > 0) {
          receipt.addDiscount(discountAmount)
        }
      }
    }
  }
}
