package santaChristmasList.operations

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import santaChristmasList.operations.dependencies.Factory
import santaChristmasList.operations.dependencies.Inventory
import santaChristmasList.operations.dependencies.WishList
import santaChristmasList.operations.models.Child
import santaChristmasList.operations.models.Gift

class BusinessTest {

    @Test
    fun child_wish_not_found_error_message_is_extracted() {
        val timmy = Child("Timmy")

        val wishList = object : WishList {
            override fun identifyGift(child: Child): Gift? = null
        }
        val factory = object : Factory {
            override fun findManufacturedGift(gift: Gift): Gift? = null
        }
        val inventory = object : Inventory {
            override fun pickUpGift(barCode: String): Gift? = null
        }

        val business = Business(factory, inventory, wishList)

        val message = loadGiftAndExtractInnerErrorMessage(business, timmy)

        assertEquals("No wish found for child: Timmy", message)
    }

    @Test
    fun gift_not_manufactured_error_message_is_extracted() {
        val timmy = Child("Timmy")
        val wishedGift = Gift("Lego Death Star", "BARCODE-123")

        val wishList = object : WishList {
            override fun identifyGift(child: Child): Gift? = wishedGift
        }
        val factory = object : Factory {
            override fun findManufacturedGift(gift: Gift): Gift? = null
        }
        val inventory = object : Inventory {
            override fun pickUpGift(barCode: String): Gift? = null
        }

        val business = Business(factory, inventory, wishList)

        val message = loadGiftAndExtractInnerErrorMessage(business, timmy)

        assertEquals("Gift has not been manufactured: Lego Death Star", message)
    }

    @Test
    fun gift_out_of_stock_error_message_is_extracted() {
        val timmy = Child("Timmy")
        val wishedGift = Gift("Red Bike", "BARCODE-456")
        val manufacturedGift = Gift("Red Bike", "BARCODE-456")

        val wishList = object : WishList {
            override fun identifyGift(child: Child): Gift? = wishedGift
        }
        val factory = object : Factory {
            override fun findManufacturedGift(gift: Gift): Gift? = manufacturedGift
        }
        val inventory = object : Inventory {
            override fun pickUpGift(barCode: String): Gift? = null
        }

        val business = Business(factory, inventory, wishList)

        val message = loadGiftAndExtractInnerErrorMessage(business, timmy)

        assertEquals("Gift out of stock: Red Bike", message)
    }

    private fun loadGiftAndExtractInnerErrorMessage(business: Business, child: Child): String {
        val ex = assertThrows(Business.BusinessException::class.java) {
            business.loadGiftsInSleigh(child)
        }
        val cause = ex.cause ?: error("Expected inner cause")
        return cause.message ?: ""
    }
}
