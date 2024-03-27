package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FacultyRepository extends JpaRepository<Faculty, UUID> {
}