package by.pintusau.country.number.resolver.services;

import by.pintusau.country.number.resolver.dto.Phone;
import by.pintusau.country.number.resolver.entities.Country;

public interface PhoneService {

    Country getCountryByNumber(Phone phone);
}
