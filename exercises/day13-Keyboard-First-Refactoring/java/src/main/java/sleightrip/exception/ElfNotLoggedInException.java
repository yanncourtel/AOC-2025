package sleightrip.exception;

/**
 * Thrown when no elf is logged into the workshop system.
 */
public class ElfNotLoggedInException extends RuntimeException {

    public ElfNotLoggedInException() {
        super("No elf is logged into the workshop system");
    }
}
