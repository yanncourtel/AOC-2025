namespace SantaScheduling;

public enum Zone { FarWest, Americas, EuropeOrEast }

public static class SantaScheduler
{
    // Zone boundaries (boundary value belongs to the eastern zone)
    private const double FarWestThreshold = -5;  // tz < -5 → Far-west;  tz == -5 → Americas
    private const double EuropeThreshold  =  0;  // tz < 0  → Americas;  tz == 0  → Europe

    private static bool IsFarWest(double tz)  => tz < FarWestThreshold;  // UTC-6 to UTC-12
    private static bool IsAmericas(double tz) => tz < EuropeThreshold;   // UTC-5 to UTC-0.x

    private static Zone GetZone(double tz) =>
        IsFarWest(tz)  ? Zone.FarWest :
        IsAmericas(tz) ? Zone.Americas :
                         Zone.EuropeOrEast;

    // Europe/Asia gets an early evening slot — children are sent to bed at 20:00
    // Americas gets a late night slot — children stay up later, Santa arrives at 23:00
    private const int EarlyEveningSlot = 20;
    private const int LateNightSlot    = 23;

    public const  int DeliveryYear = 2024;
    private const int December     = 12;
    private const int ChristmasEve = 24;
    private const int ChristmasDay = 25;
    private const int BoxingDay    = 26;

    // Europe/Asia departs early morning — roads are quiet at 02:00
    // Americas departs pre-dawn — Santa needs the extra hours for the larger land mass
    private const int EarlyMorningSlot = 2;
    private const int PreDawnSlot      = 4;

    public static DateTime GetArrivalTime(double timezoneOffset) =>
        GetZone(timezoneOffset) switch
        {
            Zone.FarWest      => new DateTime(DeliveryYear, December, ChristmasDay, LateNightSlot,    0, 0),
            Zone.Americas     => new DateTime(DeliveryYear, December, ChristmasEve, LateNightSlot,    0, 0),
            Zone.EuropeOrEast => new DateTime(DeliveryYear, December, ChristmasEve, EarlyEveningSlot, 0, 0),
            _                 => throw new ArgumentOutOfRangeException(nameof(timezoneOffset))
        };

    public static DateTime GetDepartureTime(double timezoneOffset) =>
        GetZone(timezoneOffset) switch
        {
            Zone.FarWest      => new DateTime(DeliveryYear, December, BoxingDay,    PreDawnSlot,      0, 0),
            Zone.Americas     => new DateTime(DeliveryYear, December, ChristmasDay, PreDawnSlot,      0, 0),
            Zone.EuropeOrEast => new DateTime(DeliveryYear, December, ChristmasDay, EarlyMorningSlot, 0, 0),
            _                 => throw new ArgumentOutOfRangeException(nameof(timezoneOffset))
        };
}
