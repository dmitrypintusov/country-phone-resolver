package by.pintusau.country.number.resolver.controllers;

import by.pintusau.country.number.resolver.dto.Phone;
import by.pintusau.country.number.resolver.entities.Country;
import by.pintusau.country.number.resolver.services.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/phone")
@AllArgsConstructor
public class PhoneController {

    private final PhoneService phoneService;

    @PostMapping
    @Operation(summary = "Get country by phone number")
    public Country getCountryByPhone(@Valid @RequestBody Phone number) {
        return phoneService.getCountryByNumber(number);
    }
}
