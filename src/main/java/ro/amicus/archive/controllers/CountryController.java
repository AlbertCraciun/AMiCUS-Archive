package ro.amicus.archive.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.Country;
import ro.amicus.archive.servicies.CountryService;

import java.util.List;
import java.util.UUID;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getCountries() {
        return countryService.getCountries();
    }

    @GetMapping("/countries/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String getCountry(@PathVariable String name) {
        return countryService.getCountry(name);
    }

    @PostMapping("/add-countries")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCountry(@RequestBody String name) {
        countryService.addCountry(name);
    }

}
