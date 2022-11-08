package by.pintusau.country.number.resolver.utils;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PhoneNumberUtils {

    public String cleanNumber(String number) {
        return Optional.ofNullable(number)
                .map(n -> n.replaceAll("(.?\\+)|\\s", ""))
                .orElse(null);
    }
}
