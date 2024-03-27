package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
}