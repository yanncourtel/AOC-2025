import { SleightTrip } from './SleightTrip';
import { Elf } from '../elf/Elf';
import { CollaboratorCallException } from '../exception/Exceptions';

/**
 * Legacy static data access object for sleigh trips.
 */
export class SleighTripDAO {
    static findTripsByUser(user: Elf): SleightTrip[] {
        throw new CollaboratorCallException(
            'SleighTripDAO is a static collaborator and should not be used directly in unit tests'
        );
    }
}
