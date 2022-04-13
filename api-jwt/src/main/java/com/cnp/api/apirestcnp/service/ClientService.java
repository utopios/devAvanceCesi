package com.cnp.api.apirestcnp.service;

import com.cnp.api.apirestcnp.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

  public List<Client> getAllClient();
  public Optional<Client> getClientById(Integer idClient);
  public Client modifyClient(Client client);
  public boolean deleteClient(Integer idClient);
  public Client createClient(Client client);


}
