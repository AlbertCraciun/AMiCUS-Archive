package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.ProjectRelation;

import java.util.UUID;

public interface ProjectRelationRepository extends JpaRepository<ProjectRelation, UUID> {
}