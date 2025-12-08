using System.Collections.Generic;

namespace SantaMarket.Model
{
    public class FakeCatalog : ISantamarketCatalog
    {
        private readonly Dictionary<string, Product> products = new Dictionary<string, Product>();
        private readonly Dictionary<string, double> prices = new Dictionary<string, double>();

        public void AddProduct(Product product, double price)
        {
            products[product.Name] = product;
            prices[product.Name] = price;
        }

        public double GetUnitPrice(Product product)
        {
            return prices[product.Name];
        }
    }
}
