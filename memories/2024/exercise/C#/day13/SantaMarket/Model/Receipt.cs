namespace SantaMarket.Model
{
    public class Receipt
    {
        private double totalPrice = 0.0;
        private double totalDiscount = 0.0;

        public void AddProduct(double price)
        {
            totalPrice += price;
        }

        public void AddDiscount(double discount)
        {
            totalDiscount += discount;
        }

        public double GetTotalPrice()
        {
            return totalPrice - totalDiscount;
        }
    }
}
