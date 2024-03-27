package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}