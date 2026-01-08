package santaChristmasList.operations.dependencies;

import santaChristmasList.operations.models.Child;
import santaChristmasList.operations.models.Gift;

public interface WishList {
    Gift identifyGift(Child child);
}
