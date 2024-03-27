package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UniversityRepository extends JpaRepository<University, UUID> {
}