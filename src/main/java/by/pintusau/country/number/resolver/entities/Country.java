package by.pintusau.country.number.resolver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    Long id;
    String name;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "codes", joinColumns = @JoinColumn(name = "code_id"))
    @Column(name = "code")
    @JsonIgnore
    List<String> codes;
}
