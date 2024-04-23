package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.Faculty;

import java.util.UUID;

public interface FacultyRepository extends JpaRepository<Faculty, UUID> {
}