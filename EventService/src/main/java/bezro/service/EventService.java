package bezro.service;

import bezro.model.Event;
import bezro.payload.Client;
import bezro.payload.CreateEventRequest;
import bezro.payload.UpdateEventRequest;
import bezro.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final WebClient webClient;

    @Autowired
    public EventService(EventRepository eventRepository, WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
        this.eventRepository = eventRepository;
    }

    public Mono<Void> createEvent(CreateEventRequest createEventRequest) {
        Client client = webClient.get()
                .uri("http://localhost:8080/api/clients/" + createEventRequest.getClientId())
                .retrieve()
                .bodyToMono(Client.class)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found")))
                .block();

        Boolean isVendorAvailable = webClient.get()
                .uri("http://localhost:8081/api/vendors/checkAvailability/" + createEventRequest.getVendorId())
                .retrieve()
                .bodyToMono(Boolean.class)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vendor not found")))
                .block();


        if (client != null && isVendorAvailable) {
            Event event = new Event(createEventRequest.getEventName(),
                    createEventRequest.getClientId(),
                    createEventRequest.getVendorId(),
                    createEventRequest.getEventDateTime());
            return Mono.fromRunnable(() -> eventRepository.save(event));
        } else {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid client or vendor availability"));
        }

    }

//    public Mono<Void> createEvent(CreateEventRequest createEventRequest) {
//        Mono<Client> clientMono = webClient.get()
//                .uri("http://localhost:8080/api/clients/{clientId}", createEventRequest.getClientId())
//                .retrieve()
//                .bodyToMono(Client.class)
//                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found")));
//        ;
//
//        Mono<Boolean> vendorAvailabilityMono = webClient.get()
//                .uri("8081/api/vendors/checkAvailability/{vendorId}", createEventRequest.getVendorId())
//                .retrieve()
//                .bodyToMono(Boolean.class)
//                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vendor not found")));
//        ;
//
//        Mono.zip(clientMono, vendorAvailabilityMono)
//                .flatMap(tuple -> {
//                    Client client = tuple.getT1();
//                    Boolean isVendorAvailable = tuple.getT2();
//
//                    if (client != null && isVendorAvailable) {
//                        Event event = new Event(createEventRequest.getEventName(),
//                                createEventRequest.getClientId(),
//                                createEventRequest.getVendorId(),
//                                createEventRequest.getEventDateTime());
//                        return Mono.fromRunnable(() -> eventRepository.save(event));
//                    } else {
//                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid client or vendor availability"));
//                    }
//                });
//        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid client or vendor availability"));
//    }

    public Event getEventById(Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return event.get();
    }


//    public void createEvent2(CreateEventRequest createEventRequest) {
//        Event event = new Event(createEventRequest.getEventName(),
//                createEventRequest.getClientId(),
//                createEventRequest.getVendorId(),
//                createEventRequest.getEventDateTime());
//        eventRepository.save(event);
//    }

    public void changeName(UpdateEventRequest updateEventRequest, Integer id) {
        Event event = getEventById(id);
        event.setEventName(updateEventRequest.getEventName());
        eventRepository.save(event);
    }
}
