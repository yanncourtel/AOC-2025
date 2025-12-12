package password;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordValidationTest {

    private static Stream<Arguments> validElfPasswords() {
        return Stream.of(
                Arguments.of("Abcde1"),
                Arguments.of("ELfMAr1"),
                Arguments.of("XmasEL1"),        // length 7, 2 upper, 1 digit
                Arguments.of("aBcdef1")         // mixed case, 1 digit
        );
    }

    @MethodSource("validElfPasswords")
    @ParameterizedTest
    void success_for_a_valid_elf_password(String password) {
        assertThat(PasswordValidation.validate(password))
                .as("Expected valid elf password: %s", password)
                .isTrue();
    }

    @Nested
    class FailWhen {

        private static Stream<Arguments> invalidElfPasswords() {
            return Stream.of(
                    Arguments.of("", "Too short"),
                    Arguments.of("Abc1", "Too short"),
                    Arguments.of("abcdef", "No uppercase, no digit"),
                    Arguments.of("abcde1", "No uppercase"),
                    Arguments.of("ABCDEF", "No digit"),
                    Arguments.of("Abcde12", "More than one digit"),
                    Arguments.of("abcdE2f3", "More than one digit"),
                    Arguments.of((String) null, "Null password")
            );
        }

        @MethodSource("invalidElfPasswords")
        @ParameterizedTest
        void invalid_elf_passwords(String password, String reason) {
            assertThat(PasswordValidation.validate(password))
                    .as(reason)
                    .isFalse();
        }
    }
}
