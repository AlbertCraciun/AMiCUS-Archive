package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.servicies.UniversityService;

import java.util.UUID;

@RestController
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/universities")
    public String getUniversities() {
        return universityService.getUniversities();
    }

    @GetMapping("/universities/{id}")
    public String getUniversityById(@PathVariable UUID id) {
        return universityService.getUniversityById(id);
    }

    @PostMapping("/universities")
    public String addUniversity(@RequestBody String university) {
        return universityService.addUniversity(university);
    }

}
