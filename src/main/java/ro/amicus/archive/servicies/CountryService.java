package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.Country;
import ro.amicus.archive.repositories.CountryRepository;

import java.util.List;

@Slf4j
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<String> getCountries() {
        return countryRepository.findAll().stream().map(Country::getCountryName).toList();
    }

    public String getCountry(String name) {
        return countryRepository.findByCountryName(name).getCountryName();
    }

    public void addCountry(String countryName) {
        Country country = new Country();
        country.setCountryName(countryName);
        countryRepository.save(country);
    }

}
