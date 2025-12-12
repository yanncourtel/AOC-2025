package sleightrip.trip

import sleightrip.exception.ElfNotLoggedInException
import sleightrip.elf.Elf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SleighTripServiceShould {

    @Test
    fun `throw exception when no elf is logged in`() {
        // given
        val tripService = object : SleighTripService() {
            override fun getLoggedUser(): Elf? {
                return null // no elf logged in
            }
        }
        val targetElf = Elf()

        // when / then
        assertThrows<ElfNotLoggedInException> {
            tripService.getTripsByUser(targetElf)
        }
    }

    //TODO: Please finish testing the getTripsByUser method!!
}
