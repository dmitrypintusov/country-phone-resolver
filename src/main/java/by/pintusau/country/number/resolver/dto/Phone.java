package by.pintusau.country.number.resolver.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {

    @NotBlank(message = "Number parameter is mandatory")
    @Digits(integer = 15, fraction = 0, message = "Phone number should contain up to 15 digits (without spaces)")
    String number;
}
