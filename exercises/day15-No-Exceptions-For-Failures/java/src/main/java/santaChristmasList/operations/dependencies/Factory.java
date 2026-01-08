package santaChristmasList.operations.dependencies;

import santaChristmasList.operations.models.Gift;

public interface Factory {
    Gift findManufacturedGift(Gift gift);
}
