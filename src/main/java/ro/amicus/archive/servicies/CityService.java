package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.City;
import ro.amicus.archive.repositories.CityRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public City getCity(UUID id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

}
