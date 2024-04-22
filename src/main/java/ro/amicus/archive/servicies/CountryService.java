package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.Country;
import ro.amicus.archive.repositories.CountryRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public Country getCountry(UUID id) {
        return countryRepository.findById(id).orElse(null);
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

}
