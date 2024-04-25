package ro.amicus.archive.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.servicies.CountryService;

import java.util.List;

@RestController
@Slf4j
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

    @PostMapping("/add-country")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCountry(@RequestBody String name) {
        log.info("Adding country CONTROLLER: " + name);
        countryService.addCountry(name);
    }

}
