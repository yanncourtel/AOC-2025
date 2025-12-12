package sleightrip.trip

import sleightrip.exception.ElfNotLoggedInException
import sleightrip.elf.Elf
import sleightrip.elf.ElfSession

open class SleighTripService {

    fun getTripsByUser(targetElf: Elf): List<SleightTrip> {
        val sleighTrips = mutableListOf<SleightTrip>()
        val loggedElf = getLoggedUser()
        var isWorkshopFriend = false
        
        if (loggedElf != null) {
            for (friend in targetElf.getFriends()) {
                if (friend == loggedElf) {
                    isWorkshopFriend = true
                    break
                }
            }
            if (isWorkshopFriend) {
                return SleighTripDAO.findTripsByUser(targetElf)
            }
            return sleighTrips
        } else {
            throw ElfNotLoggedInException()
        }
    }

    protected open fun getLoggedUser(): Elf? {
        return ElfSession.getInstance().getLoggedUser()
    }
}
