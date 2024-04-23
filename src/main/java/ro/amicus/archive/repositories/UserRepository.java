package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}