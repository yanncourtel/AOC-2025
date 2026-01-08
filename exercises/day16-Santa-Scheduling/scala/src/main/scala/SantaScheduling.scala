import java.time.LocalDateTime

/**
 * Santa Scheduling System
 * Written by Klaus Müller, 1987
 * 
 * Calculates Santa's arrival and departure times for different timezones.
 * Usage: SantaScheduling <command> <timezone>
 * Commands: a (arrive), l (leave)
 */
object SantaScheduling {
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      println("Usage: SantaScheduling <command> <timezone>")
      println("Commands:")
      println("  a - Show arrival time")
      println("  l - Show departure time")
      println("Example: SantaScheduling a -5")
      return
    }
    
    val cmd = args(0)
    val tz = args(1).toDouble
    
    cmd match {
      case "a" =>
        // NOTE: Your task is to document THIS arrival calculation only
        val arrival = LocalDateTime.of(
          2024, 12,
          24 + (if (tz < -5) 1 else 0),
          if (tz < 0) 23 else 20,
          0
        )
        println(s"Santa arrives: $arrival")
        
      case "l" =>
        // KLAUS SAYS: DO NOT TOUCH! Departure logic is still being used by North Pole systems.
        // You only need to understand arrival times for now.
        val departure = LocalDateTime.of(
          2024, 12,
          25 + (if (tz < -5) 1 else 0),
          if (tz < 0) 4 else 2,
          0
        )
        println(s"Santa departs: $departure")
        
      case _ => println(s"Unknown command: $cmd")
    }
  }
}
