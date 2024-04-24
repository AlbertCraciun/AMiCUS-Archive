package ro.amicus.archive.servicies;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.EventDTO;
import ro.amicus.archive.entities.Event;
import ro.amicus.archive.repositories.CityRepository;
import ro.amicus.archive.repositories.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;

    public EventService(EventRepository eventRepository, CityRepository cityRepository) {
        this.eventRepository = eventRepository;
        this.cityRepository = cityRepository;
    }

    public List<EventDTO> getEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> EventDTO.builder()
                .name(event.getName())
                .type(event.getType())
                .description(event.getDescription())
                .endDate(event.getEndDate())
                .startDate(event.getStartDate())
                .cityName(event.getCity().getCityName())
                .build()).toList();
    }

    public EventDTO getEvent(EventDTO eventDTO) {
        Specification<Event> spec = eventMatches(eventDTO);
        List<Event> events = eventRepository.findAll(spec);
        return events.stream().map(event -> EventDTO.builder()
                .name(event.getName())
                .type(event.getType())
                .description(event.getDescription())
                .endDate(event.getEndDate())
                .startDate(event.getStartDate())
                .cityName(event.getCity().getCityName())
                .build()).findFirst().orElse(null);
    }

    public void addEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setType(eventDTO.getType());
        event.setDescription(eventDTO.getDescription());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());
        event.setCity(cityRepository.findByCityName(eventDTO.getCityName()));
        eventRepository.save(event);
    }

    private Specification<Event> eventMatches(EventDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getName() != null) {
                predicates.add(cb.equal(root.get("name"), criteria.getName()));
            }
            if (criteria.getType() != null) {
                predicates.add(cb.equal(root.get("type"), criteria.getType()));
            }
            if (criteria.getCityName() != null) {
                predicates.add(cb.equal(root.get("city"), criteria.getCityName()));
            }
            if (criteria.getStartDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), criteria.getStartDate()));
            }
            if (criteria.getEndDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("endDate"), criteria.getEndDate()));
            }
            if (criteria.getParticipants() != null) {
                predicates.add(cb.equal(root.get("participants"), criteria.getParticipants()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
