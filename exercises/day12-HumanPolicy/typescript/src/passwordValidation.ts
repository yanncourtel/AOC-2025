/**
 * Elf password policy (loose):
 * - minimum length: 6 characters
 * - at least one uppercase letter
 * - exactly one digit
 */
export function validate(password: string | null | undefined): boolean {
  if (password == null) {
    return false;
  }

  const MINIMUM_LENGTH = 6;

  if (password.length < MINIMUM_LENGTH) {
    return false;
  }

  let uppercaseCount = 0;
  let digitCount = 0;

  for (const ch of password) {
    if (ch >= "A" && ch <= "Z") {
      uppercaseCount++;
    }
    if (ch >= "0" && ch <= "9") {
      digitCount++;
    }
  }

  return uppercaseCount >= 1 && digitCount === 1;
}
