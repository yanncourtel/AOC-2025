using System.Collections.Generic;

namespace SantaMarket.Model
{
    public class ChristmasElf
    {
        private readonly ISantamarketCatalog catalog;
        private readonly Dictionary<Product, Offer> offers = new Dictionary<Product, Offer>();

        public ChristmasElf(ISantamarketCatalog catalog)
        {
            this.catalog = catalog;
        }

        public void AddSpecialOffer(SpecialOfferType offerType, Product product, double argument)
        {
            offers[product] = new Offer(offerType, product, argument);
        }

        public Receipt ChecksOutArticlesFrom(ShoppingSleigh sleigh)
        {
            var receipt = new Receipt();

            foreach (var item in sleigh.GetItems())
            {
                var p = item.Product;
                var quantity = item.Quantity;
                var unitPrice = catalog.GetUnitPrice(p);
                receipt.AddProduct(quantity * unitPrice);
            }

            sleigh.HandleOffers(receipt, offers, catalog);

            return receipt;
        }
    }
}
