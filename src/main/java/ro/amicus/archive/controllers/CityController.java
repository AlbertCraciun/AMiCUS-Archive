package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.dtos.CityDTO;
import ro.amicus.archive.servicies.CityService;

import java.util.List;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public List<CityDTO> getCities() {
        return cityService.getCities();
    }

    @GetMapping("/cities/{name}")
    public CityDTO getCity(@PathVariable String name) {
        return cityService.getCity(name);
    }

    @PostMapping("/add-cities")
    public void addCity(@RequestBody CityDTO cityDTO) {
        cityService.addCity(cityDTO);
    }

}
