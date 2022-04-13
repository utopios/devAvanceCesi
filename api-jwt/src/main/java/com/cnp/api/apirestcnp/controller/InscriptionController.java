package com.cnp.api.apirestcnp.controller;


import com.cnp.api.apirestcnp.exception.LocalException;
import com.cnp.api.apirestcnp.model.*;
import com.cnp.api.apirestcnp.service.ClientService;
import com.cnp.api.apirestcnp.service.ContratService;
import com.cnp.api.apirestcnp.service.InscriptionService;
import com.cnp.api.apirestcnp.service.ReseauDistributionService;
import com.cnp.api.apirestcnp.utils.RandomPoliceAssurance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class InscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(InscriptionController.class);

    @Autowired
    InscriptionService inscriptionService;

    @Autowired
    ReseauDistributionService reseauDistributionService;

    @Autowired
    ClientService clientService;

    @Autowired
    ContratService contratService;


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/inscription/reseau/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Inscription>> getInscriptionByReseau(@PathVariable("id") Integer idReseau) throws LocalException {
        logger.info("Methode getInscriptionByReseau");
        Optional<ReseauDistribution> reseauDistribution = reseauDistributionService.getReseauDistributionId(idReseau);
        if (!reseauDistribution.isPresent()) {
            logger.error("Reseau not exist");
            throw new LocalException("Reseau " + idReseau + " not exist ");
        }

        List<Inscription> inscriptions = inscriptionService.getInscriptionByReseau(reseauDistribution.get());

        return new ResponseEntity<List<Inscription>>(inscriptions, HttpStatus.OK);
    }

    @RequestMapping(value = "/inscription/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Inscription>> getInscriptionByClient(@PathVariable("id") Integer idClient) throws LocalException {
        logger.info("Methode getInscriptionByClient");
        Optional<Client> client = clientService.getClientById(idClient);
        if (!client.isPresent()) {
            logger.error("Client not exist");
            throw new LocalException("Client " + idClient + " not exist ");
        }

        List<Inscription> inscriptions = inscriptionService.getInscriptionByClient(client.get());
        return new ResponseEntity<>(inscriptions, HttpStatus.OK);
    }

    @RequestMapping(value = "/inscription/contrat/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Inscription>> getInscriptionByContrat(@PathVariable("id") Integer idContrat) throws LocalException {
        logger.info("Methode getInscriptionByContrat");
        Optional<Contrat> contrat = contratService.getContratById(idContrat);
        if (!contrat.isPresent()) {
            logger.error("Contrat not exist");
            throw new LocalException("Contrat " + idContrat + " is not exist");
        }
        List<Inscription> inscriptions = inscriptionService.getInscriptionByContrat(contrat.get());
        return new ResponseEntity<>(inscriptions, HttpStatus.OK);
    }

    @RequestMapping(value = "/inscription-creation/client/{id}/contrat/{id2}/reseau/{id3}", method = RequestMethod.POST)
    public Inscription createInscription(@PathVariable("id") Integer idClient,
                                         @PathVariable("id2") Integer idContrat,
                                         @PathVariable("id3") Integer idReseau,
                                         @RequestBody Inscription inscription) throws LocalException {

        logger.info("Methode createInscription");

        Optional<ReseauDistribution> reseauDistribution = reseauDistributionService.getReseauDistributionId(idReseau);
        Optional<Client> client = clientService.getClientById(idClient);
        Optional<Contrat> contrat = contratService.getContratById(idContrat);

        if (!reseauDistribution.isPresent() || !client.isPresent() || !contrat.isPresent()) {
            logger.error("Problem with idReseau or idClient or idContrat");
            throw new LocalException("reseau " + idReseau + " or client " + idClient + " or contrat " + idContrat + " not exist");

        }

        String police = RandomPoliceAssurance.generateString();
        inscription.setNumeroPolice(police);
        inscription.setContrat(contrat.get());
        inscription.setClient(client.get());
        inscription.setReseauDistribution(reseauDistribution.get());

        return inscriptionService.createInscription(inscription);


    }

    @RequestMapping(value = "/delete-inscription/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteInscriptionById(@PathVariable("id") Integer idInscription) throws LocalException {

        boolean isDelete;
        logger.info("-- L'id de l'inscription a supprimé est : " + idInscription);
        Optional<Inscription> inscription = inscriptionService.getInscriptionById(idInscription);

        if (!inscription.isPresent()) {

            throw new LocalException("L'inscription n'existe pas");
        }
        isDelete = inscriptionService.deleteInscription(idInscription);

        if (!isDelete) {
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Inscription not deleted"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "Inscription has been deleted"), HttpStatus.OK);

    }


    @RequestMapping(value = "/inscription/{id}", method = RequestMethod.GET)
    public ResponseEntity<Inscription> getInscriptionById(@PathVariable("id") Integer idInscription) throws LocalException {
        Optional<Inscription> inscription = inscriptionService.getInscriptionById(idInscription);
        if (!inscription.isPresent()) {
            throw new LocalException("Inscription not exist");
        }
        logger.info(" --- Methode getInscriptionById --- ");
        logger.info(" --- L'id de l'inscription est " + idInscription);

        return new ResponseEntity<>(inscription.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/inscription/{dateDebut}/{dateFin}", method = RequestMethod.GET)
    public ResponseEntity<List<Inscription>> getInscriptionByDate(@PathVariable("dateDebut") Date dateDebut, @PathVariable("dateFin") Date dateFin) throws ParseException {

        logger.info("Methode inscriptionByDate");
        logger.info("date debut " + dateDebut + " date fin " + dateFin);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateFin1 = simpleDateFormat.format(dateFin);
        String dateDebut1 = simpleDateFormat.format(dateDebut);
        Date newdateFin = simpleDateFormat.parse(dateFin1);
        Date newdateDebut = simpleDateFormat.parse(dateDebut1);

        logger.error("format date debut " + newdateDebut + " format date fin " + newdateFin);

        List<Inscription> inscriptions = inscriptionService.getInscriptionBetweenDateDebutAndDateFin(newdateFin, newdateDebut);

        return new ResponseEntity<>(inscriptions, HttpStatus.OK);
    }


}
