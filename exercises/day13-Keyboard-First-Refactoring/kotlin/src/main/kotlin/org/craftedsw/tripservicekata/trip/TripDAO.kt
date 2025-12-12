package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.CollaboratorCallException
import org.craftedsw.tripservicekata.user.User

/**
 * Represents access to the sleigh trip storage.
 */
object TripDAO {

    @JvmStatic
    fun findTripsByUser(user: User): List<Trip> {
        throw CollaboratorCallException(
            "TripDAO is a static collaborator and should not be used directly in unit tests"
        )
    }
}
