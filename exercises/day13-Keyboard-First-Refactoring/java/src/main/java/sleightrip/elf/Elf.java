package sleightrip.elf;

import java.util.ArrayList;
import java.util.List;

import sleightrip.trip.SleightTrip;

/**
 * Represents an elf in Santa's workshop.
 *
 * Each elf:
 *  - has a list of workshop friends (other elves)
 *  - has a list of sleigh trips they went on to deliver presents
 */
public class Elf {

    private List<SleightTrip> sleighTrips = new ArrayList<>();
    private List<Elf> friends = new ArrayList<>();

    public List<Elf> getFriends() {
        return friends;
    }

    public void addFriend(Elf elf) {
        friends.add(elf);
    }

    public void addTrip(SleightTrip sleighTrip) {
        sleighTrips.add(sleighTrip);
    }

    public List<SleightTrip> trips() {
        return sleighTrips;
    }
}
