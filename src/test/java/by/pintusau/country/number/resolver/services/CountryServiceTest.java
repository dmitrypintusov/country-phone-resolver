package by.pintusau.country.number.resolver.services;

import by.pintusau.country.number.resolver.entities.Country;
import by.pintusau.country.number.resolver.repositories.CountryRepository;
import by.pintusau.country.number.resolver.services.impl.CountryServiceImpl;
import by.pintusau.country.number.resolver.utils.ListUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private ListUtils<Country> listUtils;

    @Mock
    private SourceLoader sourceLoader;

    @InjectMocks
    private CountryServiceImpl countryService;

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
    public void testLoadCountries() {
        List<Country> expected = List.of(belarus, belgium);
        when(sourceLoader.loadCountriesFromSource()).thenReturn(expected);
        when(countryRepository.findAll()).thenReturn(List.of(belarus));
        when(listUtils.getListDiff(anyList(), anyList())).thenReturn(List.of(belgium));
        when(countryRepository.saveAll(List.of(belgium))).thenReturn(expected);

        List<Country> actual = countryService.loadCountries();

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
