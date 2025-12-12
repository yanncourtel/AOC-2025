import { SleightTrip } from '../trip/SleightTrip';

/**
 * Represents an elf in Santa's workshop.
 *
 * Each elf:
 *  - has a list of workshop friends (other elves)
 *  - has a list of sleigh trips they went on to deliver presents
 */
export class Elf {
    private sleighTrips: SleightTrip[] = [];
    private friends: Elf[] = [];

    getFriends(): Elf[] {
        return this.friends;
    }

    addFriend(elf: Elf): void {
        this.friends.push(elf);
    }

    addTrip(sleighTrip: SleightTrip): void {
        this.sleighTrips.push(sleighTrip);
    }

    trips(): SleightTrip[] {
        return this.sleighTrips;
    }
}
