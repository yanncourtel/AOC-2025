package sleightrip.elf

import sleightrip.trip.SleightTrip
import scala.collection.mutable.ListBuffer

/**
 * Represents an elf in Santa's workshop.
 *
 * Each elf:
 *  - has a list of workshop friends (other elves)
 *  - has a list of sleigh trips they went on to deliver presents
 */
class Elf {
  private val sleighTrips = ListBuffer[SleightTrip]()
  private val friends = ListBuffer[Elf]()

  def getFriends: List[Elf] = friends.toList

  def addFriend(elf: Elf): Unit = {
    friends += elf
  }

  def addTrip(sleighTrip: SleightTrip): Unit = {
    sleighTrips += sleighTrip
  }

  def trips(): List[SleightTrip] = sleighTrips.toList
}
