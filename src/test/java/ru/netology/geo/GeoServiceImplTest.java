package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;


class GeoServiceImplTest {
    GeoServiceImpl geoService = new GeoServiceImpl();
    private static final String LOCALHOST = "127.0.0.1";
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";


    @ParameterizedTest
    @MethodSource("source")
    void byIp(String ip, Country expected) {
        Country actual = geoService.byIp(ip).getCountry();
        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(LOCALHOST, null),
                Arguments.of(MOSCOW_IP, Country.RUSSIA),
                Arguments.of(NEW_YORK_IP, Country.USA),
                Arguments.of("172.", Country.RUSSIA),
                Arguments.of("96.", Country.USA));
    }

    @Test()
    void byCoordinates() {
        double latitude = 24;
        double longitude = 137;
        Executable executable = () -> geoService.byCoordinates(latitude, longitude);
        Assertions.assertThrows(RuntimeException.class, executable);


    }
}