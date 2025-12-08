package santamarket.model

case class Product(name: String, unit: ProductUnit) {
  override def toString: String = s"Product{name='$name', unit=$unit}"
}
