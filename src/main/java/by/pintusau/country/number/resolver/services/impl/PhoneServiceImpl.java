package by.pintusau.country.number.resolver.services.impl;

import by.pintusau.country.number.resolver.dto.Phone;
import by.pintusau.country.number.resolver.entities.Country;
import by.pintusau.country.number.resolver.exceptions.CountryNotFoundException;
import by.pintusau.country.number.resolver.repositories.CountryRepository;
import by.pintusau.country.number.resolver.services.PhoneService;
import by.pintusau.country.number.resolver.utils.PhoneNumberUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    private final CountryRepository countryRepository;
    private final PhoneNumberUtils phoneNumberUtils;

    @Override
    public Country getCountryByNumber(Phone phone) {
        String number = phoneNumberUtils.cleanNumber(phone.getNumber());
        List<Country> countries = countryRepository.findAll();
        Country country = getCountryByNumber(number, countries);
        log.info("Found country {} by phone number {}", country.getName(), number);
        return country;
    }

    private Country getCountryByNumber(String number, List<Country> countries) {
        return countries.stream()
                .filter(country -> country.getCodes().stream().anyMatch(number::startsWith))
                .findFirst()
                .orElseThrow(() ->
                        new CountryNotFoundException(
                                String.format("Cannot find country by phone number %s", number)));
    }
}
