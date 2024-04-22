package ro.amicus.archive.repositories;

import ro.amicus.archive.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

}