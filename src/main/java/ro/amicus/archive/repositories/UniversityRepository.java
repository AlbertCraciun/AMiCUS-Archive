package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.amicus.archive.entities.University;

import java.util.UUID;

public interface UniversityRepository extends JpaRepository<University, UUID>, JpaSpecificationExecutor<University> {

    University findByName(String name);

}