package ro.amicus.archive.servicies;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.UniversityDTO;
import ro.amicus.archive.entities.University;
import ro.amicus.archive.repositories.CityRepository;
import ro.amicus.archive.repositories.UniversityRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final CityRepository cityRepository;

    public UniversityService(UniversityRepository universityRepository, CityRepository cityRepository) {
        this.universityRepository = universityRepository;
        this.cityRepository = cityRepository;
    }

    public List<UniversityDTO> getUniversities() {
        List<University> universities = universityRepository.findAll();
        return universities.stream().map(university -> UniversityDTO.builder()
                .universityName(university.getName())
                .cityName(university.getCity().getCityName())
                .build()).toList();
    }

    public UniversityDTO getUniversity(UniversityDTO universityDTO) {
        Specification<University> spec = universityMatches(universityDTO);
        List<University> universities = universityRepository.findAll(spec);
        return universities.stream().map(university -> UniversityDTO.builder()
                .universityName(university.getName())
                .cityName(university.getCity().getCityName())
                .build()).findFirst().orElse(null);
    }

    public void addUniversity(UniversityDTO universityDTO) {
        University university = new University();
        university.setName(universityDTO.getUniversityName());
        university.setCity(cityRepository.findByCityName(universityDTO.getCityName()));
        universityRepository.save(university);
    }

    public void updateUniversity(UniversityDTO universityDTO) {
        University university = universityRepository.findByName(universityDTO.getUniversityName());
        university.setName(universityDTO.getUniversityName());
        university.setCity(cityRepository.findByCityName(universityDTO.getCityName()));
        universityRepository.save(university);
    }

    public void deleteUniversity(UniversityDTO universityDTO) {
        University university = universityRepository.findByName(universityDTO.getUniversityName());
        universityRepository.delete(university);
    }

    private Specification<University> universityMatches(UniversityDTO universityDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (universityDTO.getUniversityName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), universityDTO.getUniversityName()));
            }
            if (universityDTO.getCityName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("city").get("cityName"), universityDTO.getCityName()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
