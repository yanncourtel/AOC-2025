import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import password.PasswordValidation

data class InvalidPassword(val password: String)
class PasswordValidationTests : FunSpec({
    context("invalid passwords") {
        withData(
            InvalidPassword(""),
            InvalidPassword("aa"),
            InvalidPassword("xxxxxxx"),
            InvalidPassword("adventofcraft"),
            InvalidPassword("p@ssw0rd"),
            InvalidPassword("ADVENTOFCRAFT"),
            InvalidPassword("Adventofcraft"),
            InvalidPassword("P@sswOrd"),
            InvalidPassword("Adventof09craft")
        ) { (password) ->
            PasswordValidation.validate(password) shouldBe false
        }
    }

    context("valid passwords") {
        withData("P@ssw0rd", "Advent0fCraft&") { password ->
            PasswordValidation.validate(password) shouldBe true
        }
    }
})