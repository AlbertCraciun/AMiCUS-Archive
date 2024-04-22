package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.Event;
import ro.amicus.archive.servicies.EventService;

import java.util.List;
import java.util.UUID;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable UUID id) {
        return eventService.getEvent(id);
    }

    @PostMapping("/add-events")
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

}
