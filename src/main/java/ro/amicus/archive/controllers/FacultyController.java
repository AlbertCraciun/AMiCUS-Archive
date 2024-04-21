package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.Faculty;

import java.util.List;

@RestController
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/faculties")
    public List<Faculty> getFaculties() {
        return facultyService.getFaculties();
    }

    @GetMapping("/faculties/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @PostMapping("/add-faculties")
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

}
