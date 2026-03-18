using Xunit;

namespace SantaScheduling.Tests;

public class SantaSchedulingTests
{
    private static DateTime Dec(int day, int hour) => new(SantaScheduler.DeliveryYear, 12, day, hour, 0, 0);

    [Theory(DisplayName = "TICKET-101: Day boundary — Hawaii (UTC-10) lands Dec 25, New York (UTC-5) lands Dec 24")]
    [InlineData(-10, 25, 23)]  // Hawaii: tz < -5 → Far-west → Dec 25 23:00
    [InlineData(-5,  24, 23)]  // New York: tz == -5, NOT < -5 → Americas → Dec 24 23:00
    public void Ticket101_DayBoundary(double timezone, int expectedDay, int expectedHour)
    {
        Assert.Equal(Dec(expectedDay, expectedHour), SantaScheduler.GetArrivalTime(timezone));
    }

    [Theory(DisplayName = "TICKET-102: Time slot — London (UTC+0) at 20:00, New York (UTC-5) at 23:00")]
    [InlineData( 0, 24, 20)]  // London: tz >= 0 → Europe zone → Dec 24 20:00
    [InlineData(-5, 24, 23)]  // New York: tz < 0 → Americas zone → Dec 24 23:00
    public void Ticket102_TimeSlot(double timezone, int expectedDay, int expectedHour)
    {
        Assert.Equal(Dec(expectedDay, expectedHour), SantaScheduler.GetArrivalTime(timezone));
    }

    [Theory(DisplayName = "TICKET-103: Boundary points — tz=-5 belongs to Americas (23:00), tz=0 belongs to Europe (20:00)")]
    [InlineData(-5, 24, 23)]  // -5 is NOT < -5 → Americas zone → Dec 24 23:00
    [InlineData( 0, 24, 20)]  // 0 is NOT < 0   → Europe zone  → Dec 24 20:00
    public void Ticket103_BoundaryPoints(double timezone, int expectedDay, int expectedHour)
    {
        Assert.Equal(Dec(expectedDay, expectedHour), SantaScheduler.GetArrivalTime(timezone));
    }
    
    [Theory(DisplayName = "TICKET-104: Fractional offsets — Newfoundland (UTC-3.5) and Mumbai (UTC+5.5) are handled correctly")]
    [InlineData(-3.5, 24, 23)]  // Newfoundland: -3.5 < 0 → Americas → Dec 24 23:00
    [InlineData( 5.5, 24, 20)]  // Mumbai: 5.5 >= 0 → Europe/East → Dec 24 20:00
    public void Ticket104_FractionalOffsets(double timezone, int expectedDay, int expectedHour)
    {
        Assert.Equal(Dec(expectedDay, expectedHour), SantaScheduler.GetArrivalTime(timezone));
    }
    
    [Theory(DisplayName = "SANTA-SPECIAL: Departure — FarWest Dec 26 04:00, Americas Dec 25 04:00, Europe Dec 25 02:00")]
    //         tz      day  hour
    // --- Far-west: departs Dec 26 04:00 ---
    [InlineData(-10,   26,   4)]  // Hawaii
    [InlineData(-6,    26,   4)]  // Central America
    // --- Americas: departs Dec 25 04:00 ---
    [InlineData(-5,    25,   4)]  // New York — boundary belongs to Americas
    [InlineData(-3.5,  25,   4)]  // Newfoundland
    // --- Europe/East: departs Dec 25 02:00 ---
    [InlineData( 0,    25,   2)]  // London — boundary belongs to Europe
    [InlineData( 5.5,  25,   2)]  // Mumbai
    [InlineData( 9,    25,   2)]  // Tokyo
    public void SantaSpecial_DepartureTime(double timezone, int expectedDay, int expectedHour)
    {
        Assert.Equal(Dec(expectedDay, expectedHour), SantaScheduler.GetDepartureTime(timezone));
    }

    [Theory(DisplayName = "TICKET-105: Full zone map — 3 rules cover UTC-12 to UTC+14")]
    //         tz      day  hour
    // --- Far-west (tz < -5): Dec 25 23:00 ---
    [InlineData(-12,   25,  23)]  // UTC-12 (Baker Island) — westernmost
    [InlineData(-10,   25,  23)]  // UTC-10 (Hawaii)
    [InlineData(-6,    25,  23)]  // UTC-6  (Central America) — far-west edge
    // --- Americas (-5 ≤ tz < 0): Dec 24 23:00 ---
    [InlineData(-5,    24,  23)]  // UTC-5  (New York) — americas edge
    [InlineData(-3.5,  24,  23)]  // UTC-3.5 (Newfoundland)
    [InlineData(-1,    24,  23)]  // UTC-1  (Azores) — americas far edge
    // --- Europe/Asia/Pacific (tz ≥ 0): Dec 24 20:00 ---
    [InlineData( 0,    24,  20)]  // UTC+0  (London) — europe edge
    [InlineData( 1,    24,  20)]  // UTC+1  (Paris)
    [InlineData( 5.5,  24,  20)]  // UTC+5.5 (Mumbai)
    [InlineData( 9,    24,  20)]  // UTC+9  (Tokyo)
    [InlineData(14,    24,  20)]  // UTC+14 (Kiribati) — easternmost
    public void Ticket105_FullZoneMap(double timezone, int expectedDay, int expectedHour)
    {
        Assert.Equal(Dec(expectedDay, expectedHour), SantaScheduler.GetArrivalTime(timezone));
    }
}
