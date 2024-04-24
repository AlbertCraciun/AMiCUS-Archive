package ro.amicus.archive.servicies;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.FacultyDTO;
import ro.amicus.archive.entities.Faculty;
import ro.amicus.archive.repositories.FacultyRepository;
import ro.amicus.archive.repositories.UniversityRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    public FacultyService(FacultyRepository facultyRepository, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.universityRepository = universityRepository;
    }

    public List<FacultyDTO> getFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        return faculties.stream().map(faculty -> FacultyDTO.builder()
                .name(faculty.getName())
                .universityName(faculty.getUniversity().getName())
                .years(faculty.getYears())
                .build()).toList();
    }

    public FacultyDTO getFaculty(FacultyDTO facultyDTO) {
        Specification<Faculty> spec = facultyMatches(facultyDTO);
        List<Faculty> faculties = facultyRepository.findAll(spec);
        return faculties.stream().map(faculty -> FacultyDTO.builder()
                .name(faculty.getName())
                .universityName(faculty.getUniversity().getName())
                .years(faculty.getYears())
                .build()).findFirst().orElse(null);
    }

    public void addFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());
        faculty.setUniversity(universityRepository.findByName(facultyDTO.getUniversityName()));
        faculty.setYears(facultyDTO.getYears());
        facultyRepository.save(faculty);
    }

    private Specification<Faculty> facultyMatches(FacultyDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getName() != null) {
                predicates.add(cb.equal(root.get("name"), criteria.getName()));
            }
            if (criteria.getUniversityName() != null) {
                predicates.add(cb.equal(root.get("university").get("name"), criteria.getUniversityName()));
            }
            if (criteria.getYears() != null) {
                predicates.add(cb.equal(root.get("years"), criteria.getYears()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
