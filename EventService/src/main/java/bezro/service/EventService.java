package bezro.service;

import bezro.model.Event;
import bezro.payload.CreateEventRequest;
import bezro.payload.UpdateEventRequest;
import bezro.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.restTemplate = new RestTemplate();
        this.eventRepository = eventRepository;
    }

    public Event getEventById(Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return event.get();
    }

    public void createEvent(CreateEventRequest createEventRequest) {
        Event vendor = new Event(createEventRequest.getEventName(),
                createEventRequest.getClientId(),
                createEventRequest.getVendorId(),
                createEventRequest.getEventDateTime());
        eventRepository.save(vendor);
    }

    public void changeName(UpdateEventRequest updateEventRequest, Integer id) {
        Event event = getEventById(id);
        event.setEventName(updateEventRequest.getEventName());
        eventRepository.save(event);
    }


//    public void createOrder(Integer bookId) {
//        String url = "http://localhost:8080/api/books/" + bookId;
//        try {
//            restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//            vendorRepository.save(new Vendor(bookId));
//        } catch (HttpClientErrorException exception) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }
}
