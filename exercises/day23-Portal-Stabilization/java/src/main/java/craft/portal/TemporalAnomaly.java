package craft.portal;

import java.util.UUID;

public record TemporalAnomaly(
    String id,
    long timestamp,
    String dimension,
    int karmaDebt,
    String karmaSignature
) {
    public static TemporalAnomaly create(long timestamp, String dimension, int karmaDebt) {
        String karmaSignature = generateKarmaSignature(timestamp, dimension, karmaDebt);
        return new TemporalAnomaly(
            UUID.randomUUID().toString(),
            timestamp,
            dimension,
            karmaDebt,
            karmaSignature
        );
    }
    
    private static String generateKarmaSignature(long timestamp, String dimension, int karmaDebt) {
        return String.format("%s-%d-%d", dimension, timestamp, karmaDebt);
    }
    
    public boolean hasSameKarmaAs(TemporalAnomaly other) {
        return this.karmaSignature.equals(other.karmaSignature);
    }
}
