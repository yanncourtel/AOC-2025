import { PortalStabilizer } from '../PortalStabilizer';
import { createTemporalAnomaly } from '../models/TemporalAnomaly';

describe('PortalStabilizer', () => {
  test('should process single anomaly', () => {
    const stabilizer = new PortalStabilizer();
    const anomaly = createTemporalAnomaly(1000, 'alpha', 5);

    const report = stabilizer.stabilize([anomaly]);

    expect(report.processedCount).toBe(1);
    expect(report.karmaBalances).toHaveLength(1);
    expect(report.karmaBalances[0].dimension).toBe('alpha');
  });

  test('should remove duplicate karma patterns in same batch', () => {
    const stabilizer = new PortalStabilizer();
    const anomaly1 = createTemporalAnomaly(1000, 'alpha', 5);
    const anomaly2 = createTemporalAnomaly(1000, 'alpha', 5); // Same karma pattern
    const anomaly3 = createTemporalAnomaly(2000, 'beta', 3);

    const report = stabilizer.stabilize([anomaly1, anomaly2, anomaly3]);

    expect(report.processedCount).toBe(2);
  });

  test('should remove duplicate karma patterns across batches', () => {
    const stabilizer = new PortalStabilizer();
    const anomaly1 = createTemporalAnomaly(1000, 'alpha', 5);

    stabilizer.stabilize([anomaly1]);

    const anomaly2 = createTemporalAnomaly(1000, 'alpha', 5); // Same karma pattern
    const anomaly3 = createTemporalAnomaly(2000, 'beta', 3);

    const report = stabilizer.stabilize([anomaly2, anomaly3]);

    expect(report.processedCount).toBe(1); // Only anomaly3 is new
    expect(stabilizer.getTotalProcessedCount()).toBe(2); // Total: anomaly1 + anomaly3
  });

  test('should calculate karma balance per dimension', () => {
    const stabilizer = new PortalStabilizer();
    const anomalies = [
      createTemporalAnomaly(1000, 'alpha', 10),
      createTemporalAnomaly(2000, 'alpha', 20),
      createTemporalAnomaly(3000, 'beta', 5),
    ];

    const report = stabilizer.stabilize(anomalies);

    expect(report.karmaBalances).toHaveLength(2);

    const alphaBalance = report.karmaBalances.find(
      (b) => b.dimension === 'alpha'
    )!;
    expect(alphaBalance.totalKarmaDebt).toBe(30);
    expect(alphaBalance.anomalyCount).toBe(2);
    expect(alphaBalance.totalKarmaDebt / alphaBalance.anomalyCount).toBe(15.0);
  });

  test('should calculate karma harmony', () => {
    const stabilizer = new PortalStabilizer();
    const anomalies = [
      createTemporalAnomaly(1000, 'alpha', 2),
      createTemporalAnomaly(2000, 'beta', 3),
    ];

    const report = stabilizer.stabilize(anomalies);

    // Average karma debt across dimensions: (2 + 3) / 2 = 2.5
    // Karma harmony: 100 - (2.5 * 10) = 75.0
    expect(report.karmaHarmony).toBe(75.0);
  });
});
