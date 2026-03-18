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
    
    [Fact(DisplayName = "TICKET-102: Investigation - Compare arrival times")]
    public void Ticket102_Investigation()
    {
        // After refactoring, investigate:
        // - London (UTC+0) arrival time
        // - New York (UTC-5) arrival time
        // - Why the 3-hour difference?
        
        Assert.True(true, "Extract the logic first, then investigate");
    }
    
    [Fact(DisplayName = "TICKET-103: Investigation - Test boundary points")]
    public void Ticket103_Investigation()
    {
        // After refactoring, test:
        // - What happens at exactly -5?
        // - What happens at exactly 0?
        // - Are they grouped with the zones before or after?
        
        Assert.True(true, "Make it testable first");
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
