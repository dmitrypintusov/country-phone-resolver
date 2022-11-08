package by.pintusau.country.number.resolver.services;

import by.pintusau.country.number.resolver.entities.Country;

import java.util.List;

public interface SourceLoader {

    List<Country> loadCountriesFromSource();
}
