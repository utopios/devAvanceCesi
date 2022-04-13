package com.cnp.api.apirestcnp.service.imp;

import com.cnp.api.apirestcnp.model.Client;
import com.cnp.api.apirestcnp.repository.ClientRepository;
import com.cnp.api.apirestcnp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> getAllClient() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Integer idClient) {
        return clientRepository.findById(idClient);
    }

    @Override
    public boolean deleteClient(Integer idClient) {
        boolean clienDelete = true;
        clientRepository.deleteById(idClient);
        clienDelete = (!clientRepository.findById(idClient).isPresent()) ? true : false;
        return clienDelete;
    }

    @Override
    public Client modifyClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
}
