import { randomUUID } from 'crypto';

export interface TemporalAnomaly {
  id: string;
  timestamp: number;
  dimension: string;
  karmaDebt: number;
  karmaSignature: string;
}

export function createTemporalAnomaly(
  timestamp: number,
  dimension: string,
  karmaDebt: number
): TemporalAnomaly {
  const karmaSignature = generateKarmaSignature(timestamp, dimension, karmaDebt);
  return {
    id: randomUUID(),
    timestamp,
    dimension,
    karmaDebt,
    karmaSignature,
  };
}

function generateKarmaSignature(
  timestamp: number,
  dimension: string,
  karmaDebt: number
): string {
  return `${dimension}-${timestamp}-${karmaDebt}`;
}

export function hasSameKarmaAs(
  anomaly1: TemporalAnomaly,
  anomaly2: TemporalAnomaly
): boolean {
  return anomaly1.karmaSignature === anomaly2.karmaSignature;
}
