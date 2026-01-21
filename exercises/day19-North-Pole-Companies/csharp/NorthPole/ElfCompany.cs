namespace NorthPole
{
    public class ElfCompany
    {
        public string Name { get; }
        public string Type { get; }
        public Region Region { get; }

        public ElfCompany(string name, string type, Region region)
        {
            Name = name;
            Type = type;
            Region = region;
        }
    }
}
