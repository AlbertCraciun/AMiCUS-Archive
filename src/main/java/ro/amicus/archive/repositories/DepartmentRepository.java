package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.Department;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}