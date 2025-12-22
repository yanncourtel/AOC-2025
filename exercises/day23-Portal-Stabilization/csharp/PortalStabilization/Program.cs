using System.Text.Json;
using PortalStabilization.Models;

var stabilizer = new PortalStabilizer();
var batch = new List<TemporalAnomaly>();
StabilizationReport? lastReport = null;

string? line;
while ((line = Console.ReadLine()) != null)
{
    var data = JsonSerializer.Deserialize<Dictionary<string, JsonElement>>(line);
    if (data == null) continue;
    
    var anomaly = TemporalAnomaly.Create(
        data["timestamp"].GetInt64(),
        data["dimension"].GetString()!,
        data["karmaDebt"].GetInt32()
    );
    batch.Add(anomaly);
    
    // Process in batches of 2000
    if (batch.Count >= 2000)
    {
        lastReport = stabilizer.Stabilize(batch);
        batch.Clear();
    }
}

// Process remaining
if (batch.Count > 0)
{
    lastReport = stabilizer.Stabilize(batch);
}

// Output aggregated results
var output = new
{
    totalProcessed = stabilizer.GetTotalProcessedCount(),
    karmaBalances = lastReport?.KarmaBalances.Select(b => new
    {
        dimension = b.Dimension,
        totalKarmaDebt = b.TotalKarmaDebt,
        anomalyCount = b.AnomalyCount
    }).ToList(),
    karmaHarmony = lastReport?.KarmaHarmony ?? 100.0
};

var json = JsonSerializer.Serialize(output, new JsonSerializerOptions
{
    WriteIndented = true,
    PropertyNamingPolicy = JsonNamingPolicy.CamelCase
});

Console.WriteLine(json);
