namespace NorthPole.Domain
{
    public class ElfCompany(string name, string type, Region region)
    {
        public string Name { get; } = name;
        public string Type { get; } = type;
        public Region Region { get; } = region;
    }
}
