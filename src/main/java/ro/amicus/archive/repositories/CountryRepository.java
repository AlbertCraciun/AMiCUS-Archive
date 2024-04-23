package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.Country;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

    Country findByCountryName(String name);

}