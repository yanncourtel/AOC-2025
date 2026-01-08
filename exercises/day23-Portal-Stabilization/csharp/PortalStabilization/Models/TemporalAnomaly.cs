namespace PortalStabilization.Models;

public record TemporalAnomaly(
    string Id,
    long Timestamp,
    string Dimension,
    int KarmaDebt,
    string KarmaSignature)
{
    public static TemporalAnomaly Create(long timestamp, string dimension, int karmaDebt)
    {
        var karmaSignature = GenerateKarmaSignature(timestamp, dimension, karmaDebt);
        return new TemporalAnomaly(
            Guid.NewGuid().ToString(),
            timestamp,
            dimension,
            karmaDebt,
            karmaSignature
        );
    }
    
    private static string GenerateKarmaSignature(long timestamp, string dimension, int karmaDebt)
    {
        return $"{dimension}-{timestamp}-{karmaDebt}";
    }
    
    public bool HasSameKarmaAs(TemporalAnomaly other)
    {
        return KarmaSignature == other.KarmaSignature;
    }
}
