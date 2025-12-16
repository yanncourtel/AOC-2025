// @ts-ignore
/**
 * Santa Scheduling System
 * Written by Klaus Müller, 1987
 * 
 * Calculates Santa's arrival time for different timezones.
 */

const args = process.argv.slice(2);

if (args.length === 0) {
  console.log('Usage: npm start -- <timezone>');
  console.log('Example: npm start -- -5');
  process.exit(0);
}

const tz = parseFloat(args[0]);
const arrival = new Date(2024, 11, 24 + (tz < -5 ? 1 : 0), tz < 0 ? 23 : 20, 0);

console.log(`Santa arrives: ${arrival}`);
