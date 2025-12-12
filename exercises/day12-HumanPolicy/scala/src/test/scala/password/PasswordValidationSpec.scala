package password

import org.scalatest.funsuite.AnyFunSuite

class PasswordValidationSpec extends AnyFunSuite {

  test("success for a valid elf password") {
    val validPasswords = Seq(
      "Abcde1",
      "ELfMAr1",
      "XmasEL1",
      "aBcdef1"
    )

    validPasswords.foreach { password =>
      assert(
        PasswordValidation.validate(password),
        s"Expected valid elf password: $password"
      )
    }
  }

  test("invalid elf passwords") {
    val invalids = Seq(
      (""                         , "Too short"),
      ("Abc1"                     , "Too short"),
      ("abcdef"                   , "No uppercase, no digit"),
      ("abcde1"                   , "No uppercase"),
      ("ABCDEF"                   , "No digit"),
      ("Abcde12"                  , "More than one digit"),
      ("abcdE2f3"                 , "More than one digit"),
      (null.asInstanceOf[String]  , "Null password")
    )

    invalids.foreach { case (password, reason) =>
      assert(!PasswordValidation.validate(password), reason)
    }
  }
}
