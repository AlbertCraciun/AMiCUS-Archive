package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.City;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {

    City findByCityName(String cityName);

}