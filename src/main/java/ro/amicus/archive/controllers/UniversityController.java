package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.dtos.UniversityDTO;
import ro.amicus.archive.servicies.UniversityService;

import java.util.List;

@RestController
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/universities")
    public List<UniversityDTO> getUniversities() {
        return universityService.getUniversities();
    }

    @GetMapping("/university")
    public UniversityDTO getUniversity(@RequestBody UniversityDTO universityDTO) {
        return universityService.getUniversity(universityDTO);
    }

    @PostMapping("/add-university")
    public void addUniversity(@RequestBody UniversityDTO universityDTO) {
        universityService.addUniversity(universityDTO);
    }

    @PutMapping("/update-university")
    public void updateUniversity(@RequestBody UniversityDTO universityDTO) {
        universityService.updateUniversity(universityDTO);
    }

    @DeleteMapping("/delete-university")
    public void deleteUniversity(@RequestBody UniversityDTO universityDTO) {
        universityService.deleteUniversity(universityDTO);
    }

}
