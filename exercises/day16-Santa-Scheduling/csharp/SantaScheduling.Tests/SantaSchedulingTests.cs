using Xunit;

namespace SantaScheduling.Tests;

public class SantaSchedulingTests
{
    private static DateTime Dec(int day, int hour) => new(2024, 12, day, hour, 0, 0);

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
    
    [Fact(DisplayName = "TICKET-104: Investigation - Mumbai and Newfoundland")]
    public void Ticket104_Investigation()
    {
        // After refactoring, test:
        // - Mumbai: UTC+5.5
        // - Newfoundland: UTC-3.5
        // - How are half-hour offsets handled?
        
        Assert.True(true, "Refactor, then investigate");
    }
    
    [Fact(DisplayName = "TICKET-105: Investigation - Map all regions")]
    public void Ticket105_Investigation()
    {
        // After refactoring, document:
        // - How many different rules are there?
        // - What timezone ranges does each rule cover?
        // - UTC-12 to UTC+14 - what's the complete picture?
        
        Assert.True(true, "Extract logic, then map the rules");
    }
}
