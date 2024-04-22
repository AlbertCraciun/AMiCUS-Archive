package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.City;
import ro.amicus.archive.servicies.CityService;

import java.util.List;
import java.util.UUID;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public List<City> getCities() {
        return cityService.getCities();
    }

    @GetMapping("/cities/{id}")
    public City getCity(@PathVariable UUID id) {
        return cityService.getCity(id);
    }

    @PostMapping("/add-cities")
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

}
