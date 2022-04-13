package com.cnp.api.apirestcnp.controller;

import com.cnp.api.apirestcnp.exception.LocalException;
import com.cnp.api.apirestcnp.model.Client;
import com.cnp.api.apirestcnp.model.Inscription;
import com.cnp.api.apirestcnp.model.Response;
import com.cnp.api.apirestcnp.service.ClientService;
import com.cnp.api.apirestcnp.service.InscriptionService;
import com.cnp.api.apirestcnp.utils.RandomString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value="/api/v1")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientService clientService;

    @Autowired
    InscriptionService inscriptionService;


    @RequestMapping(value="/clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClient(){
        logger.info(" --- Methode getAllClient ---- ");
        List<Client> clients = clientService.getAllClient();
        HttpHeaders headers = new HttpHeaders();
        if(!clients.isEmpty()){
            headers.add("Custom-header", "La liste client n'est pas vide");

        }else{
            headers.add("Custom-header", "La liste client est vide");
        }
        return new ResponseEntity<List<Client>>(clients,headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer idclient) throws LocalException {
        Optional<Client> client = clientService.getClientById(idclient);
        if (!client.isPresent()) {
            throw new LocalException("Client not exist");
        }
        logger.info(" --- Methode getClientById --- ");
        logger.info(" --- L'id du client est " + idclient);

        return new ResponseEntity<Client>(client.get(), HttpStatus.OK);
    }


    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ResponseEntity<Client> createOrUpdateClient(@RequestBody Client client) {

        logger.info(" --- Methode creationOrUpdateClient --- ");

        if (client.getId() != null) {
            Client clientModifie = clientService.modifyClient(client);
            logger.info("L'id du client modifié est " + clientModifie.getId());
            return new ResponseEntity<Client>(clientModifie, HttpStatus.OK);
        }
        RandomString gen = new RandomString(8, ThreadLocalRandom.current());

        client.setRicNumero(gen.nextString());
        Client clientCree = clientService.createClient(client);
        logger.info("L'id du client crée est " + clientCree.getId());
        return new ResponseEntity<Client>(clientCree, HttpStatus.OK);
    }



    @RequestMapping(value = "/delete-client/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteClientById(@PathVariable("id") Integer idClient) throws LocalException {

        boolean isDelete = true;
        logger.info("-- L'id du client a supprimé est : " + idClient);
        Optional<Client> client = clientService.getClientById(idClient);

        if (!client.isPresent()) {

            throw new LocalException("Le client n'existe pas");
        }

        Client client1 = clientService.getClientById(idClient).get();
        List<Inscription> inscriptions = inscriptionService.getInscriptionByClient(client1);

        if (!inscriptions.isEmpty()) {
            logger.info("-- Le nombre d'inscription est  : " + inscriptions.size());
            inscriptions.forEach(inscription -> {

                inscriptionService.deleteInscription(inscription.getId());

            });

        }

        List<Inscription> inscription2s = inscriptionService.getInscriptionByClient(client1);

        if (inscription2s.isEmpty()) {
            logger.info("-- Le nombre d'inscription est  : " + inscription2s.size());
            logger.info("-- phase de supression du client");
            isDelete = clientService.deleteClient(idClient);
            logger.info("-- Le client est supprimé : " + isDelete);

        }

        if (!isDelete) {
            return new ResponseEntity<Response>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Client not deleted"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Client has been deleted"), HttpStatus.OK);

    }


}
