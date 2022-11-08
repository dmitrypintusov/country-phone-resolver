package by.pintusau.country.number.resolver.services;

import by.pintusau.country.number.resolver.dto.Phone;
import by.pintusau.country.number.resolver.entities.Country;
import by.pintusau.country.number.resolver.exceptions.CountryNotFoundException;
import by.pintusau.country.number.resolver.repositories.CountryRepository;
import by.pintusau.country.number.resolver.services.impl.PhoneServiceImpl;
import by.pintusau.country.number.resolver.utils.PhoneNumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private PhoneNumberUtils numberUtils;

    @InjectMocks
    private PhoneServiceImpl phoneService;

    private final Country belarus = Country.builder()
            .id(1L)
            .name("Belarus")
            .codes(List.of("375"))
            .build();

    private final Country belgium = Country.builder()
            .id(1L)
            .name("Belgium")
            .codes(List.of("32"))
            .build();

    @Test
    public void testGetCountryByPhone() {
        Phone phone = Phone.builder()
                .number("+32 533 111 11")
                .build();
        String cleanedNumber = "3253311111";

        when(numberUtils.cleanNumber(phone.getNumber())).thenReturn(cleanedNumber);
        when(countryRepository.findAll()).thenReturn(List.of(belarus, belgium));

        Country country = phoneService.getCountryByNumber(phone);
        assertEquals(belgium, country);
    }

    @Test
    void testCountryNotFoundException() {
        Phone phone = Phone.builder()
                .number("+9999")
                .build();
        String cleanedNumber = "9999";

        CountryNotFoundException thrown = Assertions.assertThrows(CountryNotFoundException.class, () -> {
            when(numberUtils.cleanNumber(phone.getNumber())).thenReturn(cleanedNumber);
            when(countryRepository.findAll()).thenReturn(List.of(belarus, belgium));

            phoneService.getCountryByNumber(phone);
        });

        Assertions.assertEquals(
                String.format("Cannot find country by phone number %s", cleanedNumber),
                thrown.getMessage());
    }
}
