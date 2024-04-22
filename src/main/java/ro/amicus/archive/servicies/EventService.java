package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.Event;
import ro.amicus.archive.repositories.EventRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EventService {

    public final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(UUID id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

}
