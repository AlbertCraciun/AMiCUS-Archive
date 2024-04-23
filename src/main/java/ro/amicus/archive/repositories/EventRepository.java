package ro.amicus.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amicus.archive.entities.Event;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

}