package bezro.controller;

import bezro.model.Client;
import bezro.payload.CreateClientRequest;
import bezro.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getBook() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getBookById(@PathVariable("id") Integer id) {
        return clientService.getClient(id);
    }

    @PostMapping
    public void createBook(@RequestBody CreateClientRequest createClientRequest) {
        clientService.createClient(createClientRequest);
    }
}
