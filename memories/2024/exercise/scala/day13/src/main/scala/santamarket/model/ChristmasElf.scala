package santamarket.model

import scala.collection.mutable

class ChristmasElf(private val catalog: SantamarketCatalog) {
  private val offers = mutable.Map[Product, Offer]()

  def addSpecialOffer(offerType: SpecialOfferType, product: Product, argument: Double): Unit = {
    offers(product) = Offer(offerType, product, argument)
  }

  def checksOutArticlesFrom(sleigh: ShoppingSleigh): Receipt = {
    val receipt = new Receipt()

    for (item <- sleigh.getItems) {
      val p = item.product
      val quantity = item.quantity
      val unitPrice = catalog.getUnitPrice(p)
      receipt.addProduct(quantity * unitPrice)
    }

    sleigh.handleOffers(receipt, offers.toMap, catalog)

    receipt
  }
}
