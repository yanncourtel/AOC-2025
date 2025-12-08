using System;

namespace SantaMarket.Model
{
    public class Product
    {
        public string Name { get; }
        public ProductUnit Unit { get; }

        public Product(string name, ProductUnit unit)
        {
            Name = name;
            Unit = unit;
        }

        public override bool Equals(object obj)
        {
            if (obj == null || GetType() != obj.GetType())
                return false;

            var product = (Product)obj;
            return Name == product.Name && Unit == product.Unit;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Name, Unit);
        }

        public override string ToString()
        {
            return $"Product{{name='{Name}', unit={Unit}}}";
        }
    }
}
