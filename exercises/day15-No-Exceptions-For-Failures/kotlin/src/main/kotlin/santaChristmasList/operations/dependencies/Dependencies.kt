package santaChristmasList.operations.dependencies

import santaChristmasList.operations.models.Child
import santaChristmasList.operations.models.Gift

interface WishList {
    fun identifyGift(child: Child): Gift?
}

interface Factory {
    fun findManufacturedGift(gift: Gift): Gift?
}

interface Inventory {
    fun pickUpGift(barCode: String): Gift?
}
