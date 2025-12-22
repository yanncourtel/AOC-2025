import { KarmaBalance } from './KarmaBalance';

export interface StabilizationReport {
  processedCount: number;
  karmaBalances: KarmaBalance[];
  karmaHarmony: number;
}
