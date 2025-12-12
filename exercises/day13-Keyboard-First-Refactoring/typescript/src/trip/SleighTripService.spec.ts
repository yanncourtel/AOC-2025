import { SleighTripService } from './SleighTripService';
import { Elf } from '../elf/Elf';
import { ElfNotLoggedInException } from '../exception/Exceptions';

class TestableSleighTripService extends SleighTripService {
    constructor(private loggedUser: Elf | null) {
        super();
    }

    protected getLoggedUser(): Elf | null {
        return this.loggedUser;
    }
}

describe('SleighTripService', () => {
    it('should throw exception when no elf is logged in', () => {
        // given
        const tripService = new TestableSleighTripService(null);
        const targetElf = new Elf();

        // when / then
        expect(() => tripService.getTripsByUser(targetElf))
            .toThrow(ElfNotLoggedInException);
    });

    //TODO: Please finish testing the getTripsByUser method!!
});
