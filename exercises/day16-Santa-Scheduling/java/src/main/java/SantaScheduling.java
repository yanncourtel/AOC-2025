import java.time.LocalDateTime;

/**
 * Santa Scheduling System
 * Written by Klaus Müller, 1987
 * 
 * Calculates Santa's arrival and departure times for different timezones.
 * Usage: java SantaScheduling <command> <timezone>
 * Commands: a (arrive), l (leave)
 */
public class SantaScheduling {
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java SantaScheduling <command> <timezone>");
            System.out.println("Commands:");
            System.out.println("  a - Show arrival time");
            System.out.println("  l - Show departure time");
            System.out.println("Example: java SantaScheduling a -5");
            return;
        }
        
        String cmd = args[0];
        double tz = Double.parseDouble(args[1]);
        
        if (cmd.equals("a")) {
            // NOTE: Your task is to document THIS arrival calculation only
            LocalDateTime arrival = LocalDateTime.of(
                2024, 12, 
                24 + (tz < -5 ? 1 : 0), 
                tz < 0 ? 23 : 20, 
                0
            );
            System.out.println("Santa arrives: " + arrival);
        } else if (cmd.equals("l")) {
            // KLAUS SAYS: DO NOT TOUCH! Departure logic is still being used by North Pole systems.
            // You only need to understand arrival times for now.
            LocalDateTime departure = LocalDateTime.of(
                2024, 12,
                25 + (tz < -5 ? 1 : 0),
                tz < 0 ? 4 : 2,
                0
            );
            System.out.println("Santa departs: " + departure);
        } else {
            System.out.println("Unknown command: " + cmd);
        }
    }
}
