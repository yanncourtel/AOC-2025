package santaChristmasList.operations

import santaChristmasList.operations.dependencies.Factory
import santaChristmasList.operations.dependencies.Inventory
import santaChristmasList.operations.dependencies.WishList
import santaChristmasList.operations.models.Child
import santaChristmasList.operations.models.Gift
import santaChristmasList.operations.models.Sleigh

class Business(
    private val factory: Factory,
    private val inventory: Inventory,
    private val wishList: WishList
) {

    fun loadGiftsInSleigh(vararg children: Child): Sleigh {
        val sleigh = Sleigh()

        for (child in children) {
            try {
                loadGiftForChild(sleigh, child)
            } catch (e: Exception) {
                when (e) {
                    is ChildWishNotFoundException,
                    is GiftNotManufacturedException,
                    is GiftOutOfStockException ->
                        throw BusinessException("Unexpected error while loading sleigh", e)
                    else -> throw e
                }
            }
        }

        return sleigh
    }

    private fun loadGiftForChild(sleigh: Sleigh, child: Child) {
        val gift = wishList.identifyGift(child)
            ?: throw ChildWishNotFoundException(child)

        val manufacturedGift = factory.findManufacturedGift(gift)
            ?: throw GiftNotManufacturedException(gift)

        val finalGift = inventory.pickUpGift(manufacturedGift.barCode)
            ?: throw GiftOutOfStockException(manufacturedGift)

        sleigh.put(child, "Gift: ${finalGift.name} has been loaded!")
    }

    class ChildWishNotFoundException(child: Child) :
        RuntimeException("No wish found for child: ${child.name}")

    class GiftNotManufacturedException(gift: Gift) :
        RuntimeException("Gift has not been manufactured: ${gift.name}")

    class GiftOutOfStockException(gift: Gift) :
        RuntimeException("Gift out of stock: ${gift.name}")

    class BusinessException(message: String, cause: Throwable) :
        RuntimeException(message, cause)
}
