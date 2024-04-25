package ro.amicus.archive.controllers;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.servicies.CountryService;

import java.util.List;
import java.util.logging.Logger;

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

//    @PostMapping("/add-country")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addCountry(@RequestBody String name, HttpServletRequest request) {
//        log.info("Adding country CONTROLLER: " + name);
//        Claims claims = (Claims) request.getAttribute("claims");
//        if (claims != null && "SUPERADMIN".equals(claims.get("privilege"))) {
//            countryService.addCountry(name);
//        } else {
//            throw new RuntimeException("Unauthorized to perform this action");
//        }
//    }

    @PostMapping("/add-country")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCountry(@RequestBody String name) {
        log.info("Adding country CONTROLLER: " + name);
        countryService.addCountry(name);
    }

}
