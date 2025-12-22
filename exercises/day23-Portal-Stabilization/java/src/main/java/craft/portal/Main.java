package craft.portal;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PortalStabilizer stabilizer = new PortalStabilizer();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<TemporalAnomaly> batch = new ArrayList<>();
        
        StabilizationReport lastReport = null;
        
        String line;
        while ((line = reader.readLine()) != null) {
            Map<String, Object> data = mapper.readValue(line, Map.class);
            TemporalAnomaly anomaly = TemporalAnomaly.create(
                ((Number) data.get("timestamp")).longValue(),
                (String) data.get("dimension"),
                ((Number) data.get("karmaDebt")).intValue()
            );
            batch.add(anomaly);
            
            // Process in batches of 2000
            if (batch.size() >= 2000) {
                lastReport = stabilizer.stabilize(batch);
                batch.clear();
            }
        }
        
        // Process remaining
        if (!batch.isEmpty()) {
            lastReport = stabilizer.stabilize(batch);
        }
        
        // Output aggregated results
        // For the validator, we need to show total processed across all batches
        System.out.println("{");
        System.out.println("  \"totalProcessed\": " + stabilizer.getTotalProcessedCount() + ",");
        
        if (lastReport != null && lastReport.karmaBalances() != null) {
            System.out.println("  \"karmaBalances\": [");
            List<KarmaBalance> balances = lastReport.karmaBalances();
            for (int i = 0; i < balances.size(); i++) {
                KarmaBalance balance = balances.get(i);
                System.out.print("    {\"dimension\":\"" + balance.dimension() + "\"," +
                               "\"totalKarmaDebt\":" + balance.totalKarmaDebt() + "," +
                               "\"anomalyCount\":" + balance.anomalyCount() + "}");
                if (i < balances.size() - 1) {
                    System.out.println(",");
                } else {
                    System.out.println();
                }
            }
            System.out.println("  ],");
            System.out.println("  \"karmaHarmony\": " + lastReport.karmaHarmony());
        } else {
            System.out.println("  \"karmaBalances\": [],");
            System.out.println("  \"karmaHarmony\": 100.0");
        }
        
        System.out.println("}");
    }
}
