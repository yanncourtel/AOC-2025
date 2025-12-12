package sleightrip.trip

import sleightrip.exception.CollaboratorCallException
import sleightrip.elf.Elf

/**
 * Legacy static data access object for sleigh trips.
 */
object SleighTripDAO {
    
    fun findTripsByUser(user: Elf): List<SleightTrip> {
        throw CollaboratorCallException(
            "SleighTripDAO is a static collaborator and should not be used directly in unit tests"
        )
    }
}
