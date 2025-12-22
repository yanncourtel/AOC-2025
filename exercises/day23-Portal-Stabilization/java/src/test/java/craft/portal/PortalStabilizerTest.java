package craft.portal;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PortalStabilizerTest {
    
    @Test
    void shouldProcessSingleAnomaly() {
        PortalStabilizer stabilizer = new PortalStabilizer();
        TemporalAnomaly anomaly = TemporalAnomaly.create(1000L, "alpha", 5);
        
        StabilizationReport report = stabilizer.stabilize(List.of(anomaly));
        
        assertThat(report.processedCount()).isEqualTo(1);
        assertThat(report.karmaBalances()).hasSize(1);
        assertThat(report.karmaBalances().get(0).dimension()).isEqualTo("alpha");
    }
    
    @Test
    void shouldRemoveDuplicateKarmaPatternsInSameBatch() {
        PortalStabilizer stabilizer = new PortalStabilizer();
        TemporalAnomaly anomaly1 = TemporalAnomaly.create(1000L, "alpha", 5);
        TemporalAnomaly anomaly2 = TemporalAnomaly.create(1000L, "alpha", 5); // Same karma pattern
        TemporalAnomaly anomaly3 = TemporalAnomaly.create(2000L, "beta", 3);
        
        StabilizationReport report = stabilizer.stabilize(List.of(anomaly1, anomaly2, anomaly3));
        
        assertThat(report.processedCount()).isEqualTo(2);
    }
    
    @Test
    void shouldRemoveDuplicateKarmaPatternsAcrossBatches() {
        PortalStabilizer stabilizer = new PortalStabilizer();
        TemporalAnomaly anomaly1 = TemporalAnomaly.create(1000L, "alpha", 5);
        
        stabilizer.stabilize(List.of(anomaly1));
        
        TemporalAnomaly anomaly2 = TemporalAnomaly.create(1000L, "alpha", 5); // Same karma pattern
        TemporalAnomaly anomaly3 = TemporalAnomaly.create(2000L, "beta", 3);
        
        StabilizationReport report = stabilizer.stabilize(List.of(anomaly2, anomaly3));
        
        assertThat(report.processedCount()).isEqualTo(1); // Only anomaly3 is new
        assertThat(stabilizer.getTotalProcessedCount()).isEqualTo(2); // Total: anomaly1 + anomaly3
    }
    
    @Test
    void shouldCalculateKarmaBalancePerDimension() {
        PortalStabilizer stabilizer = new PortalStabilizer();
        List<TemporalAnomaly> anomalies = List.of(
            TemporalAnomaly.create(1000L, "alpha", 10),
            TemporalAnomaly.create(2000L, "alpha", 20),
            TemporalAnomaly.create(3000L, "beta", 5)
        );
        
        StabilizationReport report = stabilizer.stabilize(anomalies);
        
        assertThat(report.karmaBalances()).hasSize(2);
        
        KarmaBalance alphaBalance = report.karmaBalances().stream()
            .filter(b -> b.dimension().equals("alpha"))
            .findFirst()
            .orElseThrow();
        
        assertThat(alphaBalance.totalKarmaDebt()).isEqualTo(30);
        assertThat(alphaBalance.anomalyCount()).isEqualTo(2);
        assertThat(alphaBalance.averageKarmaDebt()).isEqualTo(15.0);
    }
    
    @Test
    void shouldCalculateKarmaHarmony() {
        PortalStabilizer stabilizer = new PortalStabilizer();
        List<TemporalAnomaly> anomalies = List.of(
            TemporalAnomaly.create(1000L, "alpha", 2),
            TemporalAnomaly.create(2000L, "beta", 3)
        );
        
        StabilizationReport report = stabilizer.stabilize(anomalies);
        
        // Average karma debt across dimensions: (2 + 3) / 2 = 2.5
        // Karma harmony: 100 - (2.5 * 10) = 75.0
        assertThat(report.karmaHarmony()).isEqualTo(75.0);
    }
}
