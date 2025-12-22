package craft.portal;

public record KarmaBalance(
    String dimension,
    int totalKarmaDebt,
    int anomalyCount
) {
    public double averageKarmaDebt() {
        return anomalyCount == 0 ? 0.0 : (double) totalKarmaDebt / anomalyCount;
    }
}
