package santamarket.model

data class Product(
    val name: String,
    val unit: ProductUnit
) {
    override fun toString(): String {
        return "Product{name='$name', unit=$unit}"
    }
}
