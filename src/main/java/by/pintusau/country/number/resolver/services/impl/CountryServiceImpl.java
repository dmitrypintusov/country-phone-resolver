package by.pintusau.country.number.resolver.services.impl;

import by.pintusau.country.number.resolver.entities.Country;
import by.pintusau.country.number.resolver.repositories.CountryRepository;
import by.pintusau.country.number.resolver.services.CountryService;
import by.pintusau.country.number.resolver.services.SourceLoader;
import by.pintusau.country.number.resolver.utils.ListUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final SourceLoader sourceLoader;
    private final ListUtils<Country> listUtils;

    @Override
    public List<Country> loadCountries() {
        List<Country> countries = sourceLoader.loadCountriesFromSource();
        log.info("Loaded {} countries", countries.size());
        saveOrUpdateList(countries);
        return countries;
    }

    private void saveOrUpdateList(List<Country> countries) {
        List<Country> persisted = countryRepository.findAll();
        log.info("Found {} countries in database", persisted.size());
        List<Country> saveList = listUtils.getListDiff(countries, persisted);
        List<Country> result = countryRepository.saveAll(saveList);
        log.info("Saved {} countries to database", result.size());
    }
}
