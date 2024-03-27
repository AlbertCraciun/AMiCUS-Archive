package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

}