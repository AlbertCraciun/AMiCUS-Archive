package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.CityDTO;
import ro.amicus.archive.entities.City;
import ro.amicus.archive.repositories.CityRepository;
import ro.amicus.archive.repositories.CountryRepository;

import java.util.List;

@Slf4j
@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public List<CityDTO> getCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().map(city -> CityDTO.builder()
                .cityName(city.getCityName())
                .countryName(city.getCountry().getCountryName())
                .build()).toList();
    }

    public CityDTO getCity(String name) {
        City city = cityRepository.findByCityName(name);
        return CityDTO.builder()
                .cityName(city.getCityName())
                .countryName(city.getCountry().getCountryName())
                .build();
    }

    public void addCity(CityDTO cityDTO) {
        City city = new City();
        city.setCityName(cityDTO.getCityName());
        city.setCountry(countryRepository.findByCountryName(cityDTO.getCountryName()));
        cityRepository.save(city);
    }

}
