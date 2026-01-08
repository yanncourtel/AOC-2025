package santaChristmasList.operations.dependencies;

import santaChristmasList.operations.models.Gift;

public interface Inventory {
    Gift pickUpGift(String barCode);
}
