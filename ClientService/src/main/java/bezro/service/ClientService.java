package bezro.service;

import bezro.model.Client;
import bezro.payload.CreateClientRequest;
import bezro.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(Integer id){
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return client.get();
    }

    public void createClient(CreateClientRequest createClientRequest) {
        Client client = new Client(createClientRequest.getName(),
                createClientRequest.getEmail());
        clientRepository.save(client);
    }

}
