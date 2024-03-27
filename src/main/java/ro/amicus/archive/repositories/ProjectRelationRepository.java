package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.ProjectRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProjectRelationRepository extends JpaRepository<ProjectRelation, UUID> {
}