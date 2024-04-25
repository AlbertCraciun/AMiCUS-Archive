package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.amicus.archive.entities.MyUser;

import java.util.Optional;
import java.util.UUID;

public interface MyUserRepository extends JpaRepository<MyUser, UUID>, JpaSpecificationExecutor<MyUser> {
    Optional<MyUser> findByEmail(String email);

}