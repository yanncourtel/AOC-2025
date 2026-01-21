using System;

namespace NorthPole.Domain;

public enum Region
{
    NorthPole,
    Nordic,
    Alpine,
    Arctic
}

public static class RegionExtensions
{
    extension(Region)
    {
        public static Region FromString(string regionString) => ParseRegion(regionString);

        private static Region ParseRegion(string regionString) => regionString switch
        {
            "north-pole" => Region.NorthPole,
            "nordic" => Region.Nordic,
            "alpine" => Region.Alpine,
            "arctic" => Region.Arctic,
            _ => throw new ArgumentException($"Unknown region: {regionString}")
        };
    }
}