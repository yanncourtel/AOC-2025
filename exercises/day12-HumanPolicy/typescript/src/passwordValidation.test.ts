import { validate } from "./passwordValidation";

describe("PasswordValidation (elf policy)", () => {
  test.each([
    ["Abcde1"],
    ["ELfMAr1"],
    ["XmasEL1"],
    ["aBcdef1"],
  ])("accepts valid elf password %s", (password) => {
    expect(validate(password)).toBe(true);
  });

  describe("fails when", () => {
    test.each([
      ["", "Too short"],
      ["Abc1", "Too short"],
      ["abcdef", "No uppercase, no digit"],
      ["abcde1", "No uppercase"],
      ["ABCDEF", "No digit"],
      ["Abcde12", "More than one digit"],
      ["abcdE2f3", "More than one digit"],
      [null, "Null password"],
    ])("invalid elf password %s (%s)", (password, reason) => {
      expect(validate(password as any)).toBe(false);
    });
  });
});
