namespace SantaScheduling;

public static class SantaScheduler
{
    // Zone boundaries (boundary value belongs to the eastern zone)
    private const double FarWestThreshold = -5;  // tz < -5 → Far-west;  tz == -5 → Americas
    private const double EuropeThreshold  =  0;  // tz < 0  → Americas;  tz == 0  → Europe

    private static bool IsFarWest(double tz)  => tz < FarWestThreshold;  // UTC-6 to UTC-12
    private static bool IsAmericas(double tz) => tz < EuropeThreshold;   // UTC-5 to UTC-0.x (includes Far-west for hour slot)

    // Europe/Asia gets an early evening slot — children are sent to bed at 20:00
    // Americas gets a late night slot — children stay up later, Santa arrives at 23:00
    private const int EarlyEveningSlot = 20;
    private const int LateNightSlot    = 23;

    public const  int DeliveryYear  = 2024;
    private const int December      = 12;
    private const int ChristmasEve  = 24;
    private const int ChristmasDay  = 25;

    public static DateTime GetArrivalTime(double timezoneOffset) =>
        new(DeliveryYear,
            December,
            IsFarWest(timezoneOffset) ? ChristmasDay : ChristmasEve,
            IsAmericas(timezoneOffset) ? LateNightSlot : EarlyEveningSlot,
            0,
            0);
}
