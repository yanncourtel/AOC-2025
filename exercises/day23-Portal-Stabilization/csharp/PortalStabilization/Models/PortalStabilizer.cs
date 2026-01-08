namespace PortalStabilization.Models;

public class PortalStabilizer
{
    private readonly List<TemporalAnomaly> _processedAnomalies = new();
    
    /// <summary>
    /// Process a batch of temporal anomalies to balance karma and stabilize the portal.
    /// Removes duplicate karma patterns and calculates dimensional karma balance.
    /// </summary>
    public StabilizationReport Stabilize(List<TemporalAnomaly> anomalies)
    {
        var uniqueAnomalies = RemoveDuplicateKarmaPatterns(anomalies);
        var karmaBalances = CalculateKarmaBalances(uniqueAnomalies);
        
        _processedAnomalies.AddRange(uniqueAnomalies);
        
        return new StabilizationReport(
            uniqueAnomalies.Count,
            karmaBalances,
            CalculateKarmaHarmony(karmaBalances)
        );
    }
    
    /// <summary>
    /// Remove duplicate karma patterns from the batch.
    /// An anomaly is a duplicate if it has the same karma signature as another.
    /// </summary>
    private List<TemporalAnomaly> RemoveDuplicateKarmaPatterns(List<TemporalAnomaly> anomalies)
    {
        var unique = new List<TemporalAnomaly>();
        
        foreach (var anomaly in anomalies)
        {
            var isDuplicate = false;
            
            // Check against all already processed anomalies
            foreach (var processed in _processedAnomalies)
            {
                if (anomaly.HasSameKarmaAs(processed))
                {
                    isDuplicate = true;
                    break;
                }
            }
            
            // Check against anomalies in current batch
            if (!isDuplicate)
            {
                foreach (var existing in unique)
                {
                    if (anomaly.HasSameKarmaAs(existing))
                    {
                        isDuplicate = true;
                        break;
                    }
                }
            }
            
            if (!isDuplicate)
            {
                unique.Add(anomaly);
            }
        }
        
        return unique;
    }
    
    /// <summary>
    /// Calculate karma balance for each dimension.
    /// </summary>
    private List<KarmaBalance> CalculateKarmaBalances(List<TemporalAnomaly> anomalies)
    {
        var dimensions = new List<string>();
        
        // Find all unique dimensions
        foreach (var anomaly in anomalies)
        {
            var found = false;
            foreach (var dim in dimensions)
            {
                if (dim == anomaly.Dimension)
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                dimensions.Add(anomaly.Dimension);
            }
        }
        
        // Calculate karma balance for each dimension
        var balances = new List<KarmaBalance>();
        foreach (var dimension in dimensions)
        {
            var totalKarmaDebt = 0;
            var count = 0;
            
            foreach (var anomaly in anomalies)
            {
                if (anomaly.Dimension == dimension)
                {
                    totalKarmaDebt += anomaly.KarmaDebt;
                    count++;
                }
            }
            
            balances.Add(new KarmaBalance(dimension, totalKarmaDebt, count));
        }
        
        return balances;
    }
    
    /// <summary>
    /// Calculate overall karma harmony across all dimensions.
    /// Lower average karma debt = higher harmony = more stable portal.
    /// </summary>
    private double CalculateKarmaHarmony(List<KarmaBalance> balances)
    {
        if (balances.Count == 0)
        {
            return 100.0;
        }
        
        var totalAverageKarmaDebt = 0.0;
        foreach (var balance in balances)
        {
            totalAverageKarmaDebt += balance.AverageKarmaDebt();
        }
        
        var meanKarmaDebt = totalAverageKarmaDebt / balances.Count;
        return Math.Max(0.0, 100.0 - (meanKarmaDebt * 10));
    }
    
    public int GetTotalProcessedCount() => _processedAnomalies.Count;
}
