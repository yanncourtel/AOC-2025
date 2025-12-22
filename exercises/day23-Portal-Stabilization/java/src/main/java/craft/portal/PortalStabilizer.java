package craft.portal;

import java.util.ArrayList;
import java.util.List;

public class PortalStabilizer {
    
    private final List<TemporalAnomaly> processedAnomalies;
    
    public PortalStabilizer() {
        this.processedAnomalies = new ArrayList<>();
    }
    
    /**
     * Process a batch of temporal anomalies to balance karma and stabilize the portal.
     * Removes duplicate karma patterns and calculates dimensional karma balance.
     */
    public StabilizationReport stabilize(List<TemporalAnomaly> anomalies) {
        List<TemporalAnomaly> uniqueAnomalies = removeDuplicateKarmaPatterns(anomalies);
        List<KarmaBalance> karmaBalances = calculateKarmaBalances(uniqueAnomalies);
        
        processedAnomalies.addAll(uniqueAnomalies);
        
        return new StabilizationReport(
            uniqueAnomalies.size(),
            karmaBalances,
            calculateKarmaHarmony(karmaBalances)
        );
    }
    
    /**
     * Remove duplicate karma patterns from the batch.
     * An anomaly is a duplicate if it has the same karma signature as another.
     */
    private List<TemporalAnomaly> removeDuplicateKarmaPatterns(List<TemporalAnomaly> anomalies) {
        List<TemporalAnomaly> unique = new ArrayList<>();
        
        for (TemporalAnomaly anomaly : anomalies) {
            boolean isDuplicate = false;
            
            // Check against all already processed anomalies
            for (TemporalAnomaly processed : processedAnomalies) {
                if (anomaly.hasSameKarmaAs(processed)) {
                    isDuplicate = true;
                    break;
                }
            }
            
            // Check against anomalies in current batch
            if (!isDuplicate) {
                for (TemporalAnomaly existing : unique) {
                    if (anomaly.hasSameKarmaAs(existing)) {
                        isDuplicate = true;
                        break;
                    }
                }
            }
            
            if (!isDuplicate) {
                unique.add(anomaly);
            }
        }
        
        return unique;
    }
    
    /**
     * Calculate karma balance for each dimension.
     */
    private List<KarmaBalance> calculateKarmaBalances(List<TemporalAnomaly> anomalies) {
        List<String> dimensions = new ArrayList<>();
        
        // Find all unique dimensions
        for (TemporalAnomaly anomaly : anomalies) {
            boolean found = false;
            for (String dim : dimensions) {
                if (dim.equals(anomaly.dimension())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                dimensions.add(anomaly.dimension());
            }
        }
        
        // Calculate karma balance for each dimension
        List<KarmaBalance> balances = new ArrayList<>();
        for (String dimension : dimensions) {
            int totalKarmaDebt = 0;
            int count = 0;
            
            for (TemporalAnomaly anomaly : anomalies) {
                if (anomaly.dimension().equals(dimension)) {
                    totalKarmaDebt += anomaly.karmaDebt();
                    count++;
                }
            }
            
            balances.add(new KarmaBalance(dimension, totalKarmaDebt, count));
        }
        
        return balances;
    }
    
    /**
     * Calculate overall karma harmony across all dimensions.
     * Lower average karma debt = higher harmony = more stable portal.
     */
    private double calculateKarmaHarmony(List<KarmaBalance> balances) {
        if (balances.isEmpty()) {
            return 100.0;
        }
        
        double totalAverageKarmaDebt = 0.0;
        for (KarmaBalance balance : balances) {
            totalAverageKarmaDebt += balance.averageKarmaDebt();
        }
        
        double meanKarmaDebt = totalAverageKarmaDebt / balances.size();
        return Math.max(0.0, 100.0 - (meanKarmaDebt * 10));
    }
    
    public int getTotalProcessedCount() {
        return processedAnomalies.size();
    }
}
