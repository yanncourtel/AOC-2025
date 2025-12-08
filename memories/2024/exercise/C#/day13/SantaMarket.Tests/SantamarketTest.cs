using Xunit;

namespace SantaMarket.Model.Tests
{
    public class SantamarketTest
    {
        [Fact]
        public void NoDiscount()
        {
            var catalog = new FakeCatalog();
            var teddyBear = new Product("teddyBear", ProductUnit.Each);
            catalog.AddProduct(teddyBear, 1.0);

            var sleigh = new ShoppingSleigh();
            sleigh.AddItemQuantity(teddyBear, 3);

            var elf = new ChristmasElf(catalog);
            var receipt = elf.ChecksOutArticlesFrom(sleigh);

            Assert.Equal(3.0, receipt.GetTotalPrice(), 2);
        }

        [Fact]
        public void TenPercentDiscount()
        {
            var catalog = new FakeCatalog();
            var turkey = new Product("turkey", ProductUnit.Kilo);
            catalog.AddProduct(turkey, 2.0);

            var sleigh = new ShoppingSleigh();
            sleigh.AddItemQuantity(turkey, 2.0);

            var elf = new ChristmasElf(catalog);
            elf.AddSpecialOffer(SpecialOfferType.TenPercentDiscount, turkey, 10.0);
            var receipt = elf.ChecksOutArticlesFrom(sleigh);

            Assert.Equal(3.6, receipt.GetTotalPrice(), 2);  // 4.0 - 0.4
        }

        [Fact]
        public void ThreeForTwoDiscount()
        {
            var catalog = new FakeCatalog();
            var teddyBear = new Product("teddyBear", ProductUnit.Each);
            catalog.AddProduct(teddyBear, 1.0);

            var elf = new ChristmasElf(catalog);
            elf.AddSpecialOffer(SpecialOfferType.ThreeForTwo, teddyBear, 0.0);

            var sleigh = new ShoppingSleigh();
            sleigh.AddItemQuantity(teddyBear, 3);

            var receipt = elf.ChecksOutArticlesFrom(sleigh);

            Assert.Equal(2.0, receipt.GetTotalPrice(), 2);  // Buy 3, pay for 2
        }
    }
}
