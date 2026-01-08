package sleightrip.trip

import sleightrip.exception.ElfNotLoggedInException
import sleightrip.elf.{Elf, ElfSession}
import scala.collection.mutable.ListBuffer

class SleighTripService {

  def getTripsByUser(targetElf: Elf): List[SleightTrip] = {
    val sleighTrips = ListBuffer[SleightTrip]()
    val loggedElf = getLoggedUser
    var isWorkshopFriend = false

    if (loggedElf != null) {
      for (friend <- targetElf.getFriends) {
        if (friend == loggedElf) {
          isWorkshopFriend = true
        }
      }
      if (isWorkshopFriend) {
        return SleighTripDAO.findTripsByUser(targetElf)
      }
      sleighTrips.toList
    } else {
      throw new ElfNotLoggedInException()
    }
  }

  protected def getLoggedUser: Elf = {
    ElfSession.getInstance().getLoggedUser
  }
}
