namespace SantaScheduling;

public static class SantaScheduler
{
    private static bool IsFarWest(double tz) => tz < -5;   // UTC-6 to UTC-12
    private static bool IsAmericas(double tz) => tz < 0;   // UTC-5 to UTC-0.x

    public static DateTime GetArrivalTime(double timezoneOffset) =>
        new(2024, 
            12,
            IsFarWest(timezoneOffset) ? 25 : 24,
            IsAmericas(timezoneOffset) ? 23 : 20,
            0, 
            0);
}
