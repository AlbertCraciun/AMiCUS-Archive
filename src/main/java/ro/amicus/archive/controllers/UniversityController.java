package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;

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
    public String getUniversityById(@PathVariable Long id) {
        return universityService.getUniversityById(id);
    }

    @PostMapping("/universities")
    public String addUniversity(@RequestBody String university) {
        return universityService.addUniversity(university);
    }

}
