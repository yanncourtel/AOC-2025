using SantaScheduling;

/**
 * Santa Scheduling System
 * Written by Klaus Müller, 1987
 * 
 * Calculates Santa's arrival and departure times for different timezones.
 * Usage: SantaScheduling <command> <timezone>
 * Commands: a (arrive), l (leave) — see ArriveCommand / DepartCommand constants below
 */

const string arriveCommand  = "a";
const string departCommand  = "l";

if (args.Length < 2)
{
    Console.WriteLine("Usage: SantaScheduling <command> <timezone>");
    Console.WriteLine("Commands:");
    Console.WriteLine($"  {arriveCommand} - Show arrival time");
    Console.WriteLine($"  {departCommand} - Show departure time");
    Console.WriteLine($"Example: SantaScheduling {arriveCommand} -5");
    return;
}

var cmd = args[0];
var tz = double.Parse(args[1]);

Console.WriteLine(cmd switch
{
    arriveCommand => $"Santa arrives: {SantaScheduler.GetArrivalTime(tz)}",
    departCommand => $"Santa departs: {SantaScheduler.GetDepartureTime(tz)}",
    _             => $"Unknown command: {cmd}"
});
