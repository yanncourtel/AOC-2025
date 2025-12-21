import java.io.*;
import java.util.*;

class TimelineEvent {
    String timelineId;
    String eventType;
    
    public TimelineEvent(String timelineId, String eventType) {
        this.timelineId = timelineId;
        this.eventType = eventType;
    }
}

class TemporalProcessor {
    private int processedCount = 0;
    
    public void processEvent(TimelineEvent event) {
        // Simulate temporal processing time
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // THE BUG: This is not thread-safe!
        // Multiple threads can read and write processedCount simultaneously
        processedCount++;
        
        System.out.println("Processing '" + event.eventType + 
                         "' in " + event.timelineId + 
                         " (Total processed: " + processedCount + ")");
    }
    
    public int getProcessedCount() {
        return processedCount;
    }
}

public class TimeloopProcessor {
    
    public static List<TimelineEvent> readTimelines(String filename) throws IOException {
        List<TimelineEvent> events = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    events.add(new TimelineEvent(parts[0].trim(), parts[1].trim()));
                }
            }
        }
        
        return events;
    }
    
    public static void processTimelines(List<TimelineEvent> events) {
        TemporalProcessor processor = new TemporalProcessor();
        
        // Process timelines in parallel to handle multiple realities
        events.parallelStream().forEach(event -> {
            processor.processEvent(event);
        });
        
        System.out.println("\n=== Temporal Processing Report ===");
        System.out.println("Expected events: " + events.size());
        System.out.println("Actually processed: " + processor.getProcessedCount());
    }
    
    public static void main(String[] args) {
        try {
            List<TimelineEvent> events = readTimelines("timelines.txt");
            processTimelines(events);
        } catch (IOException e) {
            System.err.println("Error reading timeline data: " + e.getMessage());
        }
    }
}
