package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.amicus.archive.entities.Faculty;

import java.util.UUID;

public interface FacultyRepository extends JpaRepository<Faculty, UUID>, JpaSpecificationExecutor<Faculty> {
    Faculty findByName(String faculty);
}