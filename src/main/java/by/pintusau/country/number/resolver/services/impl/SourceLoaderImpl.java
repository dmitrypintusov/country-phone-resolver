package by.pintusau.country.number.resolver.services.impl;

import by.pintusau.country.number.resolver.configs.CountryProperties;
import by.pintusau.country.number.resolver.entities.Country;
import by.pintusau.country.number.resolver.mapper.CountryMapper;
import by.pintusau.country.number.resolver.services.SourceLoader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SourceLoaderImpl implements SourceLoader {

    private final CountryProperties countryProperties;
    private final CountryMapper countryMapper;
    @Override
    public List<Country> loadCountriesFromSource() {
        log.info("Started loading countries from source: {}", countryProperties.getUrl());
        try {
            Document document = Jsoup.connect(countryProperties.getUrl()).get();
            Elements table = document.selectXpath(countryProperties.getTableSelector());

            return table.stream()
                    .skip(1) // header element
                    .map(countryMapper::getCountry)
                    .toList();
        } catch (Exception e) {
            log.error("Cannot load countries from {}", countryProperties.getUrl(), e);
        }

        return Collections.emptyList();
    }
}
