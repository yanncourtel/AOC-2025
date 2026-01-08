namespace SantaMarket.Model
{
    public class ProductQuantity
    {
        public Product Product { get; }
        public double Quantity { get; }

        public ProductQuantity(Product product, double quantity)
        {
            Product = product;
            Quantity = quantity;
        }
    }
}
