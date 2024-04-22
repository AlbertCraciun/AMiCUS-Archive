package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {

    public Branch findByName(String name);

    public Optional<Branch> findById(UUID id);



}