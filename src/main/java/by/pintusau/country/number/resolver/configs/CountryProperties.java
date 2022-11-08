package by.pintusau.country.number.resolver.configs;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "country")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryProperties {

    String url;
    String tableSelector;
    String nameSelector;
    String codeSelector;
}
