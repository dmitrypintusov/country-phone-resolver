package by.pintusau.country.number.resolver.repositories;

import by.pintusau.country.number.resolver.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
