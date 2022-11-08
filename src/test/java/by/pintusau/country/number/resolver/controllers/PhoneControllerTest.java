package by.pintusau.country.number.resolver.controllers;

import by.pintusau.country.number.resolver.dto.Phone;
import by.pintusau.country.number.resolver.entities.Country;
import by.pintusau.country.number.resolver.exceptions.CountryNotFoundException;
import by.pintusau.country.number.resolver.services.PhoneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhoneController.class)
public class PhoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PhoneService phoneService;

    @Test
    public void testGetCountryByPhone() throws Exception {
        Phone phone = Phone.builder()
                .number("+3253311111")
                .build();
        Country belgium = Country.builder()
                .id(1L)
                .name("Belgium")
                .codes(List.of("32"))
                .build();

        when(phoneService.getCountryByNumber(phone)).thenReturn(belgium);

        this.mockMvc.perform(post("/phone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(phone)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Belgium"));
    }

    @Test
    public void testGetCountryByPhoneWithSpaces() throws Exception {
        Phone phone = Phone.builder()
                .number("+325 331 1111")
                .build();

        this.mockMvc.perform(post("/phone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(phone)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.number")
                        .value("Phone number should contain up to 15 digits (without spaces)"));
    }

    @Test
    public void testGetCountryByPhoneWithoutNumber() throws Exception {
        Phone phone = Phone.builder()
                .build();

        this.mockMvc.perform(post("/phone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(phone)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.number")
                        .value("Number parameter is mandatory"));
    }

    @Test
    public void testGetCountryByPhoneNotFound() throws Exception {
        Phone phone = Phone.builder()
                .number("+9999")
                .build();

        String message = "Cannot find country by phone number";
        when(phoneService.getCountryByNumber(any())).thenThrow(new CountryNotFoundException(message));

        this.mockMvc.perform(post("/phone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(phone)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(message));
    }
}
