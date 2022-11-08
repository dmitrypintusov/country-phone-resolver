package by.pintusau.country.number.resolver.configs;

import by.pintusau.country.number.resolver.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartupListener {

    private final CountryService countryService;

    @EventListener(ApplicationStartedEvent.class)
    public void onStartup() {
        countryService.loadCountries();
    }
}
