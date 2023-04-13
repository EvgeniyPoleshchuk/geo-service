package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

class LocalizationServiceImplTest {
    LocalizationServiceImpl lsi = new LocalizationServiceImpl();

    @ParameterizedTest
    @MethodSource("source")
    void locale(Country country) {
        String expected = lsi.locale(country);
        String actual;
        if (country == Country.RUSSIA) {
            actual = "Добро пожаловать";
        } else {
            actual = "Welcome";
        }
        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(Country.RUSSIA),
                Arguments.of(Country.USA),
                Arguments.of(Country.RUSSIA));
    }
}