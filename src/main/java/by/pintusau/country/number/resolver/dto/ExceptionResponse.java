package by.pintusau.country.number.resolver.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionResponse {

    String message;
}
