package santamarket.model;

import java.util.*;

public class ChristmasElf {
    private final SantamarketCatalog catalog;
    private final Map<Product, Offer> offers = new HashMap<>();

    public ChristmasElf(SantamarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        offers.put(product, new Offer(offerType, product, argument));
    }

    public Receipt checksOutArticlesFrom(ShoppingSleigh sleigh) {
        Receipt receipt = new Receipt();
        
        for (ProductQuantity item : sleigh.getItems()) {
            Product p = item.getProduct();
            double quantity = item.getQuantity();
            double unitPrice = catalog.getUnitPrice(p);
            receipt.addProduct(quantity * unitPrice);
        }
        
        sleigh.handleOffers(receipt, offers, catalog);
        
        return receipt;
    }
}
