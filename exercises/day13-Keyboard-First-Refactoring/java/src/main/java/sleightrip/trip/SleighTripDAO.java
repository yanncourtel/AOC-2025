package sleightrip.trip;

import java.util.List;

import sleightrip.exception.CollaboratorCallException;
import sleightrip.elf.Elf;

/**
 * Legacy static data access object for sleigh trips.
 */
public class SleighTripDAO {

    public static List<SleightTrip> findTripsByUser(Elf user) {
        throw new CollaboratorCallException(
                "SleighTripDAO is a static collaborator and should not be used directly in unit tests"
        );
    }
}
