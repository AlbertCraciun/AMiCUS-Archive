package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.Branch;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {

    Branch findByCity_CityName(String name);


}