package password;

/**
 * For now, this class implements the *elf* password policy (loose).
 *
 * The rules are:
 * - minimum length: 6 characters
 * - at least one uppercase letter
 * - exactly one digit
 *
 * Humans will later get a stricter policy. Part of the exercise is to evolve
 * this implementation so different policies can coexist without duplication.
 */
public class PasswordValidation {

    private static final int MINIMUM_LENGTH = 6;

    public static boolean validate(String password) {
        if (password == null) {
            return false;
        }

        if (password.length() < MINIMUM_LENGTH) {
            return false;
        }

        int uppercaseCount = 0;
        int digitCount = 0;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercaseCount++;
            }
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        // Elf rules: at least one uppercase, exactly one digit
        return uppercaseCount >= 1 && digitCount == 1;
    }

    private PasswordValidation() {
        // utility class, no instances
    }
}
