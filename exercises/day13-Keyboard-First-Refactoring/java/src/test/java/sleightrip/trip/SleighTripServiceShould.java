package sleightrip.trip;

import sleightrip.exception.ElfNotLoggedInException;
import sleightrip.elf.Elf;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SleighTripServiceShould {

    @Test
    void throw_exception_when_no_elf_is_logged_in() {
        // given
        SleighTripService tripService = new SleighTripService() {
            protected Elf getLoggedUser() {
                return null; // no elf logged in
            }
        };
        Elf targetElf = new Elf();

        // when / then
        assertThatThrownBy(() -> tripService.getTripsByUser(targetElf))
                .isInstanceOf(ElfNotLoggedInException.class);
    }

    //TODO: Please finish testing the getTripsByUser method!!
}
