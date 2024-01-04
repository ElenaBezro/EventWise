package bezro.controller;

import bezro.model.Event;
import bezro.payload.CreateEventRequest;
import bezro.payload.UpdateEventRequest;
import bezro.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/{id}")
    public Event findById(@PathVariable("id") Integer id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public void createEvent(@RequestBody CreateEventRequest createEventRequest) {
        eventService.createEvent(createEventRequest);
    }

    //TODO: use eventBuilder in order to change any field?
    @PutMapping("/{id}")
    public void changeName(@RequestBody UpdateEventRequest updateEventRequest, @PathVariable("id") Integer id) {
        eventService.changeName(updateEventRequest, id);
    }
}
