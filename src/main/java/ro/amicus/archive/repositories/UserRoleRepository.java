package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
}