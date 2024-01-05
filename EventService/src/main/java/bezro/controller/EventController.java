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

//    @PostMapping
//    public Mono<ResponseEntity<Void>> createEvent(@RequestBody CreateEventRequest createEventRequest) {
//        return eventService.createEvent(createEventRequest)
//                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).<Void>build()))
//                .onErrorResume(e -> Mono.just(handleError(e)));
//    }

    //    private ResponseEntity<Void> handleError(Throwable e) {
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status for unexpected errors
//        String errorMessage = "An unexpected error occurred";
//
//        if (e instanceof ResponseStatusException) {
//            // If it's a ResponseStatusException, extract status and message
//            ResponseStatusException responseStatusException = (ResponseStatusException) e;
//            status = responseStatusException.getStatus();
//            errorMessage = responseStatusException.getReason();
//        }
//
//        return ResponseEntity.status(status).body(errorMessage);
//    }
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
