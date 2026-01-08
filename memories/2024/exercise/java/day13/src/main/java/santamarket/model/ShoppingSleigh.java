package santamarket.model;

import java.util.*;

public class ShoppingSleigh {
    private final List<ProductQuantity> items = new ArrayList<>();

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
    }

    public List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    public void handleOffers(Receipt receipt, Map<Product, Offer> offers, SantamarketCatalog catalog) {
        Map<Product, Double> productQuantities = new HashMap<>();
        for (ProductQuantity item : items) {
            Product p = item.getProduct();
            if (productQuantities.containsKey(p)) {
                productQuantities.put(p, productQuantities.get(p) + item.getQuantity());
            } else {
                productQuantities.put(p, item.getQuantity());
            }
        }

        for (Product p : productQuantities.keySet()) {
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                double unitPrice = catalog.getUnitPrice(p);
                int quantityAsInt = (int) quantity;
                double discountAmount = 0;

                if (offer.offerType == SpecialOfferType.THREE_FOR_TWO) {
                    if (quantityAsInt > 2) {
                        int numberOfSets = quantityAsInt / 3;
                        discountAmount = numberOfSets * unitPrice;
                    }
                } else if (offer.offerType == SpecialOfferType.TEN_PERCENT_DISCOUNT) {
                    discountAmount = quantity * unitPrice * 0.1;
                }

                if (discountAmount > 0) {
                    receipt.addDiscount(discountAmount);
                }
            }
        }
    }
}
