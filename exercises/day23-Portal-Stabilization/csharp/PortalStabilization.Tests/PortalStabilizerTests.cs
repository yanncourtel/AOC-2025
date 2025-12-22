using FluentAssertions;
using PortalStabilization.Models;
using Xunit;

namespace PortalStabilization.Tests;

public class PortalStabilizerTests
{
    [Fact]
    public void ShouldProcessSingleAnomaly()
    {
        var stabilizer = new PortalStabilizer();
        var anomaly = TemporalAnomaly.Create(1000L, "alpha", 5);
        
        var report = stabilizer.Stabilize(new List<TemporalAnomaly> { anomaly });
        
        report.ProcessedCount.Should().Be(1);
        report.KarmaBalances.Should().HaveCount(1);
        report.KarmaBalances[0].Dimension.Should().Be("alpha");
    }
    
    [Fact]
    public void ShouldRemoveDuplicateKarmaPatternsInSameBatch()
    {
        var stabilizer = new PortalStabilizer();
        var anomaly1 = TemporalAnomaly.Create(1000L, "alpha", 5);
        var anomaly2 = TemporalAnomaly.Create(1000L, "alpha", 5); // Same karma pattern
        var anomaly3 = TemporalAnomaly.Create(2000L, "beta", 3);
        
        var report = stabilizer.Stabilize(new List<TemporalAnomaly> { anomaly1, anomaly2, anomaly3 });
        
        report.ProcessedCount.Should().Be(2);
    }
    
    [Fact]
    public void ShouldRemoveDuplicateKarmaPatternsAcrossBatches()
    {
        var stabilizer = new PortalStabilizer();
        var anomaly1 = TemporalAnomaly.Create(1000L, "alpha", 5);
        
        stabilizer.Stabilize(new List<TemporalAnomaly> { anomaly1 });
        
        var anomaly2 = TemporalAnomaly.Create(1000L, "alpha", 5); // Same karma pattern
        var anomaly3 = TemporalAnomaly.Create(2000L, "beta", 3);
        
        var report = stabilizer.Stabilize(new List<TemporalAnomaly> { anomaly2, anomaly3 });
        
        report.ProcessedCount.Should().Be(1); // Only anomaly3 is new
        stabilizer.GetTotalProcessedCount().Should().Be(2); // Total: anomaly1 + anomaly3
    }
    
    [Fact]
    public void ShouldCalculateKarmaBalancePerDimension()
    {
        var stabilizer = new PortalStabilizer();
        var anomalies = new List<TemporalAnomaly>
        {
            TemporalAnomaly.Create(1000L, "alpha", 10),
            TemporalAnomaly.Create(2000L, "alpha", 20),
            TemporalAnomaly.Create(3000L, "beta", 5)
        };
        
        var report = stabilizer.Stabilize(anomalies);
        
        report.KarmaBalances.Should().HaveCount(2);
        
        var alphaBalance = report.KarmaBalances.First(b => b.Dimension == "alpha");
        alphaBalance.TotalKarmaDebt.Should().Be(30);
        alphaBalance.AnomalyCount.Should().Be(2);
        alphaBalance.AverageKarmaDebt().Should().Be(15.0);
    }
    
    [Fact]
    public void ShouldCalculateKarmaHarmony()
    {
        var stabilizer = new PortalStabilizer();
        var anomalies = new List<TemporalAnomaly>
        {
            TemporalAnomaly.Create(1000L, "alpha", 2),
            TemporalAnomaly.Create(2000L, "beta", 3)
        };
        
        var report = stabilizer.Stabilize(anomalies);
        
        // Average karma debt across dimensions: (2 + 3) / 2 = 2.5
        // Karma harmony: 100 - (2.5 * 10) = 75.0
        report.KarmaHarmony.Should().Be(75.0);
    }
}
