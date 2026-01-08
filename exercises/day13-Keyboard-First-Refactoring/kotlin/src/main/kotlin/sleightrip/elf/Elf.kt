package sleightrip.elf

import sleightrip.trip.SleightTrip

/**
 * Represents an elf in Santa's workshop.
 *
 * Each elf:
 *  - has a list of workshop friends (other elves)
 *  - has a list of sleigh trips they went on to deliver presents
 */
class Elf {
    private val sleighTrips = mutableListOf<SleightTrip>()
    private val friends = mutableListOf<Elf>()

    fun getFriends(): List<Elf> = friends

    fun addFriend(elf: Elf) {
        friends.add(elf)
    }

    fun addTrip(sleighTrip: SleightTrip) {
        sleighTrips.add(sleighTrip)
    }

    fun trips(): List<SleightTrip> = sleighTrips
}
