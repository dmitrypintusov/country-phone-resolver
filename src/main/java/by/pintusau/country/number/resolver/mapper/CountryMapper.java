package by.pintusau.country.number.resolver.mapper;

import by.pintusau.country.number.resolver.entities.Country;
import by.pintusau.country.number.resolver.configs.CountryProperties;
import by.pintusau.country.number.resolver.utils.PhoneNumberUtils;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CountryMapper {

    private final CountryProperties countryProperties;
    private final PhoneNumberUtils phoneNumberUtils;

    public Country getCountry(Element element) {
        return Country.builder()
                .name(getName(element))
                .codes(getCodes(element))
                .build();
    }

    private String getName(Element element) {
        return element.selectXpath(countryProperties.getNameSelector()).text().strip();
    }

    private List<String> getCodes(Element element) {
        List<TextNode> codeNodes = element.selectXpath(countryProperties.getCodeSelector()).textNodes();
        return codeNodes.stream()
                .map(TextNode::text)
                .map(this::fromRawCode)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<String> fromRawCode(String rawCode) {
        String[] codes = rawCode.split(",");
        return Arrays.stream(codes)
                .map(phoneNumberUtils::cleanNumber)
                .collect(Collectors.toList());
    }
}
