namespace SantaScheduling;

public static class SantaScheduler
{
    // Zone boundaries (boundary value belongs to the eastern zone)
    private const double FarWestThreshold = -5;  // tz < -5 → Far-west;  tz == -5 → Americas
    private const double EuropeThreshold   =  0;  // tz < 0  → Americas;  tz == 0  → Europe

    private static bool IsFarWest(double tz)     => tz < FarWestThreshold;  // UTC-6 to UTC-12
    private static bool IsAmericas(double tz)    => tz < EuropeThreshold;   // UTC-5 to UTC-0.x
    private static bool IsEuropeOrEast(double tz) => tz >= EuropeThreshold; // UTC+0 to UTC+14

    // Europe/Asia gets an early evening slot — children are sent to bed at 20:00
    // Americas gets a late night slot — children stay up later, Santa arrives at 23:00
    private const int EarlyEveningSlot = 20;
    private const int LateNightSlot    = 23;

    public static DateTime GetArrivalTime(double timezoneOffset) =>
        new(2024,
            12,
            IsFarWest(timezoneOffset) ? 25 : 24,
            IsAmericas(timezoneOffset) ? LateNightSlot : EarlyEveningSlot,
            0,
            0);
}
