package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.Country;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return countryService.getCountries();
    }

    @GetMapping("/countries/{id}")
    public Country getCountry(@PathVariable Long id) {
        return countryService.getCountry(id);
    }

    @PostMapping("/add-countries")
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

}
