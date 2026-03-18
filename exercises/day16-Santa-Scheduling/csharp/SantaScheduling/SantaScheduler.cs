namespace SantaScheduling;

public static class SantaScheduler
{
    public static DateTime GetArrivalTime(double timezoneOffset) =>
        new DateTime(
            2024, 12,
            24 + (timezoneOffset < -5 ? 1 : 0),
            timezoneOffset < 0 ? 23 : 20,
            0, 0);
}
