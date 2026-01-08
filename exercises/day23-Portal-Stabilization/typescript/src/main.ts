import * as readline from 'readline';
import { createTemporalAnomaly, TemporalAnomaly } from './models/TemporalAnomaly';
import { PortalStabilizer } from './PortalStabilizer';
import { StabilizationReport } from './models/StabilizationReport';

async function main() {
  const stabilizer = new PortalStabilizer();
  const batch: TemporalAnomaly[] = [];
  let lastReport: StabilizationReport | null = null;

  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false,
  });

  for await (const line of rl) {
    const data = JSON.parse(line);
    const anomaly = createTemporalAnomaly(
      data.timestamp,
      data.dimension,
      data.karmaDebt
    );
    batch.push(anomaly);

    // Process in batches of 2000
    if (batch.length >= 2000) {
      lastReport = stabilizer.stabilize(batch);
      batch.length = 0; // Clear array
    }
  }

  // Process remaining
  if (batch.length > 0) {
    lastReport = stabilizer.stabilize(batch);
  }

  // Output aggregated results
  const output = {
    totalProcessed: stabilizer.getTotalProcessedCount(),
    karmaBalances:
      lastReport?.karmaBalances.map((b) => ({
        dimension: b.dimension,
        totalKarmaDebt: b.totalKarmaDebt,
        anomalyCount: b.anomalyCount,
      })) ?? [],
    karmaHarmony: lastReport?.karmaHarmony ?? 100.0,
  };

  console.log(JSON.stringify(output, null, 2));
}

main().catch((error) => {
  console.error('Error:', error);
  process.exit(1);
});
