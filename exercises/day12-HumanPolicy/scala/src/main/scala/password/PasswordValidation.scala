package password

/**
 * Elf password policy (loose):
 * - minimum length: 6 characters
 * - at least one uppercase letter
 * - exactly one digit
 */
object PasswordValidation {

  private val MinimumLength = 6

  def validate(password: String): Boolean = {
    if (password == null) return false
    if (password.length < MinimumLength) return false

    var uppercaseCount = 0
    var digitCount = 0

    for (c <- password) {
      if (c.isUpper) uppercaseCount += 1
      if (c.isDigit) digitCount += 1
    }

    uppercaseCount >= 1 && digitCount == 1
  }
}
