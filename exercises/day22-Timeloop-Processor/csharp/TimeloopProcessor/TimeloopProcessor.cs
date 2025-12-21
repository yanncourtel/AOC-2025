public class TimelineEvent
{
    public string TimelineId { get; set; }
    public string TimelineEventName { get; set; }
    
    public TimelineEvent(string childName, string eventName)
    {
        TimelineId = childName;
        TimelineEventName = eventName;
    }
}

class TimeloopProcessorComponent
{
    private int processedCount = 0;
    
    public void ProcessEvent(TimelineEvent tlEvent)
    {
        Thread.Sleep(10);
        
        processedCount++;
        
        Console.WriteLine($"ProcessEventing event '{tlEvent.TimelineEventName}' to {tlEvent.TimelineId} " +
                         $"(Total processed: {processedCount})");
    }
    
    public int GetProcessEventedCount()
    {
        return processedCount;
    }
}

public class TimelineProcessor
{
    static List<TimelineEvent> ReadTimelineEventList(string filename)
    {
        var events = new List<TimelineEvent>();
        
        foreach (var line in File.ReadLines(filename))
        {
            var parts = line.Split(',');
            if (parts.Length == 2)
            {
                events.Add(new TimelineEvent(parts[0].Trim(), parts[1].Trim()));
            }
        }
        
        return events;
    }

    static void ProcessTimelineEvents(List<TimelineEvent> events)
    {
        var dispatcher = new TimeloopProcessorComponent();
        
        Parallel.ForEach(events, tlEvent =>
        {
            dispatcher.ProcessEvent(tlEvent);
        });
        
        Console.WriteLine("\n=== Final Count ===");
        Console.WriteLine($"Expected: {events.Count}");
        Console.WriteLine($"Actually processed: {dispatcher.GetProcessEventedCount()}");
    }
    
    static void Main(string[] args)
    {
        try
        {
            var events = ReadTimelineEventList("timelines.txt");
            ProcessTimelineEvents(events);
        }
        catch (Exception e)
        {
            Console.Error.WriteLine($"Error reading event list: {e.Message}");
        }
    }
}
