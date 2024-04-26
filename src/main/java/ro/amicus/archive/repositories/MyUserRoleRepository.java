package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.amicus.archive.entities.UserRole;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MyUserRoleRepository extends JpaRepository<UserRole, UUID> {

    @Query("SELECT ur FROM UserRole ur WHERE ur.roleName = :roleName")
    UserRole findByRoleName(@Param("roleName") String roleName);

    @Query("SELECT ur FROM UserRole ur WHERE ur.roleName = :roleName AND ur.startDate = :startDate")
    List<UserRole> findByRoleNameAndStartDate(@Param("roleName") String roleName, @Param("startDate") LocalDate startDate);

}