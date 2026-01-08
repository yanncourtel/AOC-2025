/**
 * Santa Scheduling System
 * Written by Klaus Müller, 1987
 * 
 * Calculates Santa's arrival and departure times for different timezones.
 * Usage: SantaScheduling <command> <timezone>
 * Commands: a (arrive), l (leave)
 */

if (args.Length < 2)
{
    Console.WriteLine("Usage: SantaScheduling <command> <timezone>");
    Console.WriteLine("Commands:");
    Console.WriteLine("  a - Show arrival time");
    Console.WriteLine("  l - Show departure time");
    Console.WriteLine("Example: SantaScheduling a -5");
    return;
}

string cmd = args[0];
double tz = double.Parse(args[1]);

if (cmd == "a")
{
    // NOTE: Your task is to document THIS arrival calculation only
    DateTime arrival = new DateTime(
        2024, 12,
        24 + (tz < -5 ? 1 : 0),
        tz < 0 ? 23 : 20,
        0, 0
    );
    Console.WriteLine($"Santa arrives: {arrival}");
}
else if (cmd == "l")
{
    // KLAUS SAYS: DO NOT TOUCH! Departure logic is still being used by North Pole systems.
    // You only need to understand arrival times for now.
    DateTime departure = new DateTime(
        2024, 12,
        25 + (tz < -5 ? 1 : 0),
        tz < 0 ? 4 : 2,
        0, 0
    );
    Console.WriteLine($"Santa departs: {departure}");
}
else
{
    Console.WriteLine($"Unknown command: {cmd}");
}
