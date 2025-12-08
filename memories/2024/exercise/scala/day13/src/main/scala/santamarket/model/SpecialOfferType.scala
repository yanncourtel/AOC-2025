package santamarket.model

sealed trait SpecialOfferType

object SpecialOfferType {
  case object ThreeForTwo extends SpecialOfferType
  case object TenPercentDiscount extends SpecialOfferType
}
