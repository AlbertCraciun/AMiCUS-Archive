package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
}