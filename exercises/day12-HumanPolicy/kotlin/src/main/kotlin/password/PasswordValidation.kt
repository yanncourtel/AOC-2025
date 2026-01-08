package password

/**
 * Elf password policy (loose):
 * - minimum length: 6 characters
 * - at least one uppercase letter
 * - exactly one digit
 */
object PasswordValidation {

    private const val MINIMUM_LENGTH = 6

    @JvmStatic
    fun validate(password: String?): Boolean {
        if (password == null) return false
        if (password.length < MINIMUM_LENGTH) return false

        var uppercaseCount = 0
        var digitCount = 0

        for (c in password) {
            if (c.isUpperCase()) {
                uppercaseCount++
            }
            if (c.isDigit()) {
                digitCount++
            }
        }

        return uppercaseCount >= 1 && digitCount == 1
    }
}
