namespace SantaMarket.Model
{
    public class Offer
    {
        public SpecialOfferType OfferType { get; }
        public Product Product { get; }
        public double Argument { get; }

        public Offer(SpecialOfferType offerType, Product product, double argument)
        {
            OfferType = offerType;
            Product = product;
            Argument = argument;
        }
    }
}
