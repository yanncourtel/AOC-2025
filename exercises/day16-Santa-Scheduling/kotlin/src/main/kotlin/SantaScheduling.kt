import java.time.LocalDateTime

/**
 * Santa Scheduling System
 * Written by Klaus Müller, 1987
 * 
 * Calculates Santa's arrival and departure times for different timezones.
 * Usage: SantaScheduling <command> <timezone>
 * Commands: a (arrive), l (leave)
 */
fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Usage: SantaScheduling <command> <timezone>")
        println("Commands:")
        println("  a - Show arrival time")
        println("  l - Show departure time")
        println("Example: SantaScheduling a -5")
        return
    }
    
    val cmd = args[0]
    val tz = args[1].toDouble()
    
    when (cmd) {
        "a" -> {
            // NOTE: Your task is to document THIS arrival calculation only
            val arrival = LocalDateTime.of(
                2024, 12,
                24 + (if (tz < -5) 1 else 0),
                if (tz < 0) 23 else 20,
                0
            )
            println("Santa arrives: $arrival")
        }
        "l" -> {
            // KLAUS SAYS: DO NOT TOUCH! Departure logic is still being used by North Pole systems.
            // You only need to understand arrival times for now.
            val departure = LocalDateTime.of(
                2024, 12,
                25 + (if (tz < -5) 1 else 0),
                if (tz < 0) 4 else 2,
                0
            )
            println("Santa departs: $departure")
        }
        else -> println("Unknown command: $cmd")
    }
}
