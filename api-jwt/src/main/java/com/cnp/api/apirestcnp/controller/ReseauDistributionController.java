package com.cnp.api.apirestcnp.controller;


import com.cnp.api.apirestcnp.exception.LocalException;
import com.cnp.api.apirestcnp.model.Inscription;
import com.cnp.api.apirestcnp.model.ReseauDistribution;
import com.cnp.api.apirestcnp.model.Response;
import com.cnp.api.apirestcnp.service.InscriptionService;
import com.cnp.api.apirestcnp.service.ReseauDistributionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class ReseauDistributionController {

    private static final Logger logger = LoggerFactory.getLogger(ReseauDistributionController.class);

    @Autowired
    ReseauDistributionService reseauDistributionService;

    @Autowired
    InscriptionService inscriptionService;

    @RequestMapping(value = "/reseau", method = RequestMethod.GET)
    public ResponseEntity<List<ReseauDistribution>> getAllReseau() {
        logger.info(" --- Methode getAllReseau --- ");
        List<ReseauDistribution> reseauDistributions = reseauDistributionService.getListReseauDistribution();
        HttpHeaders headers = new HttpHeaders();
        if (!reseauDistributions.isEmpty()) {
            headers.add("Custom-Header", "Reseau list is not empty");
        } else {
            headers.add("Custom-Header", "Client list is empty");
        }
        return new ResponseEntity<List<ReseauDistribution>>(reseauDistributions, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/reseau/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReseauDistribution> getClientById(@PathVariable("id") Integer idReseauDistribution) throws LocalException {
        Optional<ReseauDistribution> reseauDistribution = reseauDistributionService.getReseauDistributionId(idReseauDistribution);
        if (!reseauDistribution.isPresent()) {
            throw new LocalException("reseau distribution not exist");
        }
        logger.info(" --- Methode getReseauDistributionById --- ");
        logger.info(" --- L'id du Reseau est " + idReseauDistribution);

        return new ResponseEntity<ReseauDistribution>(reseauDistribution.get(), HttpStatus.OK);
    }


    @RequestMapping(value = "/reseau", method = RequestMethod.POST)
    public ResponseEntity<ReseauDistribution> createOrUpdateReseauDistribution(@RequestBody ReseauDistribution reseauDistribution) {

        logger.info(" --- Methode creationOrUpdateReseauDistribution --- ");

        if (reseauDistribution.getId() != null) {
            ReseauDistribution reseauDistributionModifie = reseauDistributionService.modifyReseauDistribution(reseauDistribution);
            logger.info("L'id du reseau de distribution modifié est " + reseauDistributionModifie.getId());
            return new ResponseEntity<ReseauDistribution>(reseauDistributionModifie, HttpStatus.OK);
        }

        ReseauDistribution reseauDistributionCree = reseauDistributionService.createReseauDistribution(reseauDistribution);
        logger.info("L'id du reseau de distribution crée est " + reseauDistributionCree.getId());
        return new ResponseEntity<ReseauDistribution>(reseauDistributionCree, HttpStatus.OK);
    }


    @RequestMapping(value = "/delete-reseau/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteReseauDistributionById(@PathVariable("id") Integer idReseauDistribution) throws LocalException {

        boolean isDelete = true;
        logger.info("-- L'id du reseau de dsitribution a supprimé est : " + idReseauDistribution);
        Optional<ReseauDistribution> reseauDistribution = reseauDistributionService.getReseauDistributionId(idReseauDistribution);

        if (!reseauDistribution.isPresent()) {

            throw new LocalException("Le reseau de distribution n'existe pas");
        }

        ReseauDistribution reseauDistribution1 = reseauDistributionService.getReseauDistributionId(idReseauDistribution).get();
        List<Inscription> inscriptions = inscriptionService.getInscriptionByReseau(reseauDistribution1);

        if (!inscriptions.isEmpty()) {
            logger.info("-- Le nombre d'inscription est  : " + inscriptions.size());
            inscriptions.forEach(inscription -> {

                inscriptionService.deleteInscription(inscription.getId());

            });

        }

        List<Inscription> inscription2s = inscriptionService.getInscriptionByReseau(reseauDistribution1);

        if (inscription2s.isEmpty()) {
            logger.info("-- Le nombre d'inscription est  : " + inscription2s.size());
            logger.info("-- phase de supression du reseau");
            isDelete = reseauDistributionService.deleteReseauDistribution(idReseauDistribution);
            logger.info("-- Le reseau est supprimé : " + isDelete);

        }


        if (!isDelete) {
            return new ResponseEntity<Response>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Reseau de distribution not deleted"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Reseau de distribution has been deleted"), HttpStatus.OK);

    }


}
