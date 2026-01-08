import { SleightTrip } from './SleightTrip';
import { SleighTripDAO } from './SleighTripDAO';
import { Elf } from '../elf/Elf';
import { ElfSession } from '../elf/ElfSession';
import { ElfNotLoggedInException } from '../exception/Exceptions';

export class SleighTripService {
    getTripsByUser(targetElf: Elf): SleightTrip[] {
        let sleighTrips: SleightTrip[] = [];
        const loggedElf = this.getLoggedUser();
        let isWorkshopFriend = false;

        if (loggedElf !== null) {
            for (const friend of targetElf.getFriends()) {
                if (friend === loggedElf) {
                    isWorkshopFriend = true;
                    break;
                }
            }
            if (isWorkshopFriend) {
                sleighTrips = SleighTripDAO.findTripsByUser(targetElf);
            }
            return sleighTrips;
        } else {
            throw new ElfNotLoggedInException();
        }
    }

    protected getLoggedUser(): Elf | null {
        return ElfSession.getInstance().getLoggedUser();
    }
}
