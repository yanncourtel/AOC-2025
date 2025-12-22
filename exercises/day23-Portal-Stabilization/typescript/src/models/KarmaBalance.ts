export interface KarmaBalance {
  dimension: string;
  totalKarmaDebt: number;
  anomalyCount: number;
}

export function averageKarmaDebt(balance: KarmaBalance): number {
  return balance.anomalyCount === 0 
    ? 0.0 
    : balance.totalKarmaDebt / balance.anomalyCount;
}
