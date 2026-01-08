package sleightrip.trip

import sleightrip.exception.ElfNotLoggedInException
import sleightrip.elf.Elf
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SleighTripServiceShould extends AnyFlatSpec with Matchers {

  "SleighTripService" should "throw exception when no elf is logged in" in {
    // given
    val tripService = new SleighTripService {
      override protected def getLoggedUser: Elf = null // no elf logged in
    }
    val targetElf = new Elf

    // when / then
    assertThrows[ElfNotLoggedInException] {
      tripService.getTripsByUser(targetElf)
    }
  }

  //TODO: Please finish testing the getTripsByUser method!!
}
