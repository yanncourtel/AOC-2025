package santaChristmasList.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import santaChristmasList.operations.dependencies.Factory;
import santaChristmasList.operations.dependencies.Inventory;
import santaChristmasList.operations.dependencies.WishList;
import santaChristmasList.operations.models.Child;
import santaChristmasList.operations.models.Gift;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessTest {

    private Factory factory;
    private Inventory inventory;
    private WishList wishList;
    private Business business;

    @BeforeEach
    void setUp() {
        factory = mock(Factory.class);
        inventory = mock(Inventory.class);
        wishList = mock(WishList.class);
        business = new Business(factory, inventory, wishList);
    }

    @Test
    void child_wish_not_found_error_message_is_extracted() {
        Child timmy = new Child("Timmy");
        when(wishList.identifyGift(timmy)).thenReturn(null);

        String errorMessage = loadGiftAndExtractInnerErrorMessage(timmy);

        assertThat(errorMessage).isEqualTo("No wish found for child: Timmy");
    }

    @Test
    void gift_not_manufactured_error_message_is_extracted() {
        Child timmy = new Child("Timmy");
        Gift wishedGift = new Gift("Lego Death Star", "BARCODE-123");
        when(wishList.identifyGift(timmy)).thenReturn(wishedGift);
        when(factory.findManufacturedGift(wishedGift)).thenReturn(null);

        String errorMessage = loadGiftAndExtractInnerErrorMessage(timmy);

        assertThat(errorMessage)
                .isEqualTo("Gift has not been manufactured: Lego Death Star");
    }

    @Test
    void gift_out_of_stock_error_message_is_extracted() {
        Child timmy = new Child("Timmy");
        Gift wishedGift = new Gift("Red Bike", "BARCODE-456");
        Gift manufacturedGift = new Gift("Red Bike", "BARCODE-456");

        when(wishList.identifyGift(timmy)).thenReturn(wishedGift);
        when(factory.findManufacturedGift(wishedGift)).thenReturn(manufacturedGift);
        when(inventory.pickUpGift(manufacturedGift.barCode())).thenReturn(null);

        String errorMessage = loadGiftAndExtractInnerErrorMessage(timmy);

        assertThat(errorMessage).isEqualTo("Gift out of stock: Red Bike");
    }

    private String loadGiftAndExtractInnerErrorMessage(Child child) {
        Business.BusinessException businessException =
                catchThrowableOfType(
                        () -> business.loadGiftsInSleigh(child),
                        Business.BusinessException.class
                );

        assertThat(businessException.getCause()).isNotNull();

        return businessException.getCause().getMessage();
    }
}
