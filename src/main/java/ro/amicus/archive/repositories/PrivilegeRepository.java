package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.Privilege;

import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
}