package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.amicus.archive.dtos.EventDTO;
import ro.amicus.archive.servicies.EventService;

import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<EventDTO> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/event")
    public EventDTO getEvent(@RequestBody EventDTO eventDTO) {
        return eventService.getEvent(eventDTO);
    }

    @PostMapping("/add-events")
    public void addEvent(@RequestBody EventDTO eventDTO) {
        eventService.addEvent(eventDTO);
    }

}
