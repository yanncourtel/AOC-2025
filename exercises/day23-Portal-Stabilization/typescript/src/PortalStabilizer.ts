import { TemporalAnomaly, hasSameKarmaAs } from './models/TemporalAnomaly';
import { KarmaBalance, averageKarmaDebt } from './models/KarmaBalance';
import { StabilizationReport } from './models/StabilizationReport';

export class PortalStabilizer {
  private processedAnomalies: TemporalAnomaly[] = [];

  /**
   * Process a batch of temporal anomalies to balance karma and stabilize the portal.
   * Removes duplicate karma patterns and calculates dimensional karma balance.
   */
  stabilize(anomalies: TemporalAnomaly[]): StabilizationReport {
    const uniqueAnomalies = this.removeDuplicateKarmaPatterns(anomalies);
    const karmaBalances = this.calculateKarmaBalances(uniqueAnomalies);

    this.processedAnomalies.push(...uniqueAnomalies);

    return {
      processedCount: uniqueAnomalies.length,
      karmaBalances,
      karmaHarmony: this.calculateKarmaHarmony(karmaBalances),
    };
  }

  /**
   * Remove duplicate karma patterns from the batch.
   * An anomaly is a duplicate if it has the same karma signature as another.
   */
  private removeDuplicateKarmaPatterns(
    anomalies: TemporalAnomaly[]
  ): TemporalAnomaly[] {
    const unique: TemporalAnomaly[] = [];

    for (const anomaly of anomalies) {
      let isDuplicate = false;

      // Check against all already processed anomalies
      for (const processed of this.processedAnomalies) {
        if (hasSameKarmaAs(anomaly, processed)) {
          isDuplicate = true;
          break;
        }
      }

      // Check against anomalies in current batch
      if (!isDuplicate) {
        for (const existing of unique) {
          if (hasSameKarmaAs(anomaly, existing)) {
            isDuplicate = true;
            break;
          }
        }
      }

      if (!isDuplicate) {
        unique.push(anomaly);
      }
    }

    return unique;
  }

  /**
   * Calculate karma balance for each dimension.
   */
  private calculateKarmaBalances(
    anomalies: TemporalAnomaly[]
  ): KarmaBalance[] {
    const dimensions: string[] = [];

    // Find all unique dimensions
    for (const anomaly of anomalies) {
      let found = false;
      for (const dim of dimensions) {
        if (dim === anomaly.dimension) {
          found = true;
          break;
        }
      }
      if (!found) {
        dimensions.push(anomaly.dimension);
      }
    }

    // Calculate karma balance for each dimension
    const balances: KarmaBalance[] = [];
    for (const dimension of dimensions) {
      let totalKarmaDebt = 0;
      let count = 0;

      for (const anomaly of anomalies) {
        if (anomaly.dimension === dimension) {
          totalKarmaDebt += anomaly.karmaDebt;
          count++;
        }
      }

      balances.push({
        dimension,
        totalKarmaDebt,
        anomalyCount: count,
      });
    }

    return balances;
  }

  /**
   * Calculate overall karma harmony across all dimensions.
   * Lower average karma debt = higher harmony = more stable portal.
   */
  private calculateKarmaHarmony(balances: KarmaBalance[]): number {
    if (balances.length === 0) {
      return 100.0;
    }

    let totalAverageKarmaDebt = 0.0;
    for (const balance of balances) {
      totalAverageKarmaDebt += averageKarmaDebt(balance);
    }

    const meanKarmaDebt = totalAverageKarmaDebt / balances.length;
    return Math.max(0.0, 100.0 - meanKarmaDebt * 10);
  }

  getTotalProcessedCount(): number {
    return this.processedAnomalies.length;
  }
}
