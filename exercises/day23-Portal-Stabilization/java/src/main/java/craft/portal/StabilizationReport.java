package craft.portal;

import java.util.List;

public record StabilizationReport(
    int processedCount,
    List<KarmaBalance> karmaBalances,
    double karmaHarmony
) {}
