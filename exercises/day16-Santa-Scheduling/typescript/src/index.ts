/**
 * Santa Scheduling System
 * Written by Klaus Müller, 1987
 * 
 * Calculates Santa's arrival and departure times for different timezones.
 * Usage: npm start -- <command> <timezone>
 * Commands: a (arrive), l (leave)
 */

const args = process.argv.slice(2);

if (args.length < 2) {
  console.log('Usage: npm start -- <command> <timezone>');
  console.log('Commands:');
  console.log('  a - Show arrival time');
  console.log('  l - Show departure time');
  console.log('Example: npm start -- a -5');
  process.exit(0);
}

const cmd = args[0];
const tz = parseFloat(args[1]);

if (cmd === 'a') {
  // NOTE: Your task is to document THIS arrival calculation only
  const arrival = new Date(
    2024, 11,  // Month is 0-indexed (11 = December)
    24 + (tz < -5 ? 1 : 0),
    tz < 0 ? 23 : 20,
    0
  );
  console.log(`Santa arrives: ${arrival.toISOString()}`);
} else if (cmd === 'l') {
  // KLAUS SAYS: DO NOT TOUCH! Departure logic is still being used by North Pole systems.
  // You only need to understand arrival times for now.
  const departure = new Date(
    2024, 11,
    25 + (tz < -5 ? 1 : 0),
    tz < 0 ? 4 : 2,
    0
  );
  console.log(`Santa departs: ${departure.toISOString()}`);
} else {
  console.log(`Unknown command: ${cmd}`);
}
