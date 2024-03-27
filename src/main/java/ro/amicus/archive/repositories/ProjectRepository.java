package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}