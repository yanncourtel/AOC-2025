namespace SantaScheduling;

public static class SantaScheduler
{
    private static bool IsFarWest(double tz) => tz < -5;  // UTC-6 to UTC-12
    private static bool IsAmericas(double tz) => tz < 0;  // UTC-5 to UTC-0.x

    // Europe/Asia gets an early evening slot — children are sent to bed at 20:00
    // Americas gets a late night slot — children stay up later, Santa arrives at 23:00
    private const int EarlyEveningSlot = 20;
    private const int LateNightSlot = 23;

    public static DateTime GetArrivalTime(double timezoneOffset) =>
        new(2024,
            12,
            IsFarWest(timezoneOffset) ? 25 : 24,
            IsAmericas(timezoneOffset) ? LateNightSlot : EarlyEveningSlot,
            0,
            0);
}
