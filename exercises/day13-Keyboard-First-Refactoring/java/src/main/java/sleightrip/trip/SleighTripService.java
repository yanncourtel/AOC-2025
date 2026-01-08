package sleightrip.trip;

import java.util.ArrayList;
import java.util.List;

import sleightrip.exception.ElfNotLoggedInException;
import sleightrip.elf.Elf;
import sleightrip.elf.ElfSession;

public class SleighTripService {

    public List<SleightTrip> getTripsByUser(Elf targetElf) throws ElfNotLoggedInException {
        List<SleightTrip> sleighTrips = new ArrayList<>();
        Elf loggedElf = getLoggedUser();
        boolean isWorkshopFriend = false;
        if (loggedElf != null) {
            for (Elf friend : targetElf.getFriends()) {
                if (friend.equals(loggedElf)) {
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

    protected Elf getLoggedUser() {
        return ElfSession.getInstance().getLoggedUser();
    }
}
