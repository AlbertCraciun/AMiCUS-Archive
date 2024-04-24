package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.amicus.archive.dtos.FacultyDTO;
import ro.amicus.archive.servicies.FacultyService;

import java.util.List;

@RestController
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/faculties")
    public List<FacultyDTO> getFaculties() {
        return facultyService.getFaculties();
    }

    @GetMapping("/faculty")
    public FacultyDTO getFaculty(@RequestBody FacultyDTO facultyDTO) {
        return facultyService.getFaculty(facultyDTO);
    }

    @PostMapping("/add-faculties")
    public void addFaculty(@RequestBody FacultyDTO facultyDTO) {
        facultyService.addFaculty(facultyDTO);
    }

}
