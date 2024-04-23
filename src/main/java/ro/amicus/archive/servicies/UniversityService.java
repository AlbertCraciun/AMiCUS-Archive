package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.University;
import ro.amicus.archive.repositories.UniversityRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> getUniversities() {
        return universityRepository.findAll();
    }

    public University getUniversityByID(UUID id) {
        return universityRepository.findById(id).orElse(null);
    }

    public University addUniversity(University university) {
        return universityRepository.save(university);
    }

}
