package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession

/**
 * Legacy implementation, tightly coupled to static collaborators.
 */
class TripService {

    @Throws(UserNotLoggedInException::class)
    fun getTripsByUser(targetElf: User): List<Trip> {
        var sleighTrips: List<Trip> = emptyList()
        val loggedElf = UserSession.getInstance().getLoggedUser()
        var isWorkshopFriend = false

        if (loggedElf != null) {
            for (friend in targetElf.getFriends()) {
                if (friend == loggedElf) {
                    isWorkshopFriend = true
                    break
                }
            }
            if (isWorkshopFriend) {
                sleighTrips = TripDAO.findTripsByUser(targetElf)
            }
            return sleighTrips
        } else {
            throw UserNotLoggedInException()
        }
    }
}
