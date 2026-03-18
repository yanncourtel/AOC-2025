using Xunit;

namespace SantaScheduling.Tests;

public class SantaSchedulingTests
{
    /*
     * TODO: Before you can write meaningful tests, you'll need to extract
     * the scheduling logic from Program.cs into a testable method.
     */
    
    [Theory(DisplayName = "TICKET-101: Day boundary — Hawaii (UTC-10) lands Dec 25, New York (UTC-5) lands Dec 24")]
    [InlineData(-10, 25)] // Hawaii: tz < -5 → Dec 25
    [InlineData(-5,  24)] // New York: tz == -5, NOT < -5 → Dec 24
    public void Ticket101_DayBoundary(double timezone, int expectedDay)
    {
        Assert.Equal(expectedDay, SantaScheduler.GetArrivalTime(timezone).Day);
    }
    
    [Theory(DisplayName = "TICKET-102: Time slot — London (UTC+0) at 20:00, New York (UTC-5) at 23:00")]
    [InlineData( 0, 20)]  // London: tz >= 0 → Europe zone → 20:00
    [InlineData(-5, 23)]  // New York: tz < 0 → Americas zone → 23:00
    public void Ticket102_TimeSlot(double timezone, int expectedHour)
    {
        Assert.Equal(expectedHour, SantaScheduler.GetArrivalTime(timezone).Hour);
    }
    
    [Theory(DisplayName = "TICKET-103: Boundary points — tz=-5 belongs to Americas (23:00), tz=0 belongs to Europe (20:00)")]
    [InlineData(-5,  12, 24, 23)]  // -5 is NOT < -5 → Americas zone → Dec 24 23:00
    [InlineData( 0,  12, 24, 20)]  // 0 is NOT < 0  → Europe zone  → Dec 24 20:00
    public void Ticket103_BoundaryPoints(double timezone, int expectedMonth, int expectedDay, int expectedHour)
    {
        var result = SantaScheduler.GetArrivalTime(timezone);
        Assert.Equal(expectedMonth, result.Month);
        Assert.Equal(expectedDay,   result.Day);
        Assert.Equal(expectedHour,  result.Hour);
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
