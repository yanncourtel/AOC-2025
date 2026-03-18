using SantaScheduling;

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
    DateTime arrival = SantaScheduler.GetArrivalTime(tz);
    Console.WriteLine($"Santa arrives: {arrival}");
}
else if (cmd == "l")
{
    DateTime departure = SantaScheduler.GetDepartureTime(tz);
    Console.WriteLine($"Santa departs: {departure}");
}
else
{
    Console.WriteLine($"Unknown command: {cmd}");
}
