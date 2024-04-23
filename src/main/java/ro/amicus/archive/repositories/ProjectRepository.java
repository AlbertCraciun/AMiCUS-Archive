package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.Project;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}