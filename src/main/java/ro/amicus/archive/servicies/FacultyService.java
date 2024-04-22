package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.Faculty;
import ro.amicus.archive.repositories.FacultyRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty getFaculty(UUID id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

}
