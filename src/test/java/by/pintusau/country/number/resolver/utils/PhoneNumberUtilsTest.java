package by.pintusau.country.number.resolver.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneNumberUtilsTest {

    @ParameterizedTest
    @MethodSource("testCleanNumberParams")
    public void testCleanNumber(String number, String expected) {
        PhoneNumberUtils phoneNumberUtils = new PhoneNumberUtils();
        String actual = phoneNumberUtils.cleanNumber(number);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testCleanNumberParams() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of("", ""),
                Arguments.of("  ", ""),
                Arguments.of(" +1 2 3", "123"),
                Arguments.of("123", "123")
        );
    }
}
