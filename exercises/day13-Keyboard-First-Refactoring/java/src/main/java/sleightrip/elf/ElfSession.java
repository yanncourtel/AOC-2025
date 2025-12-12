package sleightrip.elf;

import sleightrip.exception.CollaboratorCallException;

public class ElfSession {

    private static ElfSession instance = new ElfSession();

    public static ElfSession getInstance() {
        return instance;
    }

    public Elf getLoggedUser() {
        throw new CollaboratorCallException(
                "ElfSession is a static collaborator and should not be used directly in unit tests"
        );
    }
}
