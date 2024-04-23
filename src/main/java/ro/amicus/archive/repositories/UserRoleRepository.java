package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.UserRole;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
}