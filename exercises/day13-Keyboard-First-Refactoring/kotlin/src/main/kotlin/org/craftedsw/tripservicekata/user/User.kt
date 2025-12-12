package org.craftedsw.tripservicekata.user

import org.craftedsw.tripservicekata.trip.Trip

/**
 * Represents an elf in Santa's workshop.
 */
class User {

    private val sleighTrips = mutableListOf<Trip>()
    private val friends = mutableListOf<User>()

    fun getFriends(): List<User> = friends

    fun addFriend(elf: User) {
        friends.add(elf)
    }

    fun addTrip(sleighTrip: Trip) {
        sleighTrips.add(sleighTrip)
    }

    fun trips(): List<Trip> = sleighTrips
}
