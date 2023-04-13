package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    void send() {
        String expected = "Добро пожаловать";
        GeoServiceImpl gsi = Mockito.mock(GeoServiceImpl.class);
        String MOSCOW_IP = "172.0.32.11";
        Mockito.when(gsi.byIp(MOSCOW_IP)).thenReturn(new Location("Moskow", Country.RUSSIA, "Lebedeva", 5));
        LocalizationServiceImpl lsi = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(lsi.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        MessageSenderImpl msi = new MessageSenderImpl(gsi, lsi);
        HashMap<String, String> headers = new HashMap<>();
        String IP_ADDRESS_HEADER = "x-real-ip";
        headers.put(IP_ADDRESS_HEADER, MOSCOW_IP);
        String actual = msi.send(headers);
        Assertions.assertEquals(expected, actual);

    }
}