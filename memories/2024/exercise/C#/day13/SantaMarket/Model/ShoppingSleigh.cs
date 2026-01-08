using System;
using System.Collections.Generic;
using System.Linq;

namespace SantaMarket.Model
{
    public class ShoppingSleigh
    {
        private readonly List<ProductQuantity> items = new List<ProductQuantity>();

        public void AddItemQuantity(Product product, double quantity)
        {
            items.Add(new ProductQuantity(product, quantity));
        }

        public IReadOnlyList<ProductQuantity> GetItems()
        {
            return items.AsReadOnly();
        }

        public void HandleOffers(Receipt receipt, Dictionary<Product, Offer> offers, ISantamarketCatalog catalog)
        {
            var productQuantities = new Dictionary<Product, double>();
            foreach (var item in items)
            {
                var p = item.Product;
                if (productQuantities.ContainsKey(p))
                {
                    productQuantities[p] += item.Quantity;
                }
                else
                {
                    productQuantities[p] = item.Quantity;
                }
            }

            foreach (var p in productQuantities.Keys)
            {
                var quantity = productQuantities[p];
                if (offers.ContainsKey(p))
                {
                    var offer = offers[p];
                    var unitPrice = catalog.GetUnitPrice(p);
                    var quantityAsInt = (int)quantity;
                    var discountAmount = 0.0;

                    if (offer.OfferType == SpecialOfferType.ThreeForTwo)
                    {
                        if (quantityAsInt > 2)
                        {
                            var numberOfSets = quantityAsInt / 3;
                            discountAmount = numberOfSets * unitPrice;
                        }
                    }
                    else if (offer.OfferType == SpecialOfferType.TenPercentDiscount)
                    {
                        discountAmount = quantity * unitPrice * 0.1;
                    }

                    if (discountAmount > 0)
                    {
                        receipt.AddDiscount(discountAmount);
                    }
                }
            }
        }
    }
}
