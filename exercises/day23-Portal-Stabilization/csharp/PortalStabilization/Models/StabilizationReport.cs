namespace PortalStabilization.Models;

public record StabilizationReport(
    int ProcessedCount,
    List<KarmaBalance> KarmaBalances,
    double KarmaHarmony
);
