package com.cnp.api.apirestcnp.service.imp;


import com.cnp.api.apirestcnp.model.Client;
import com.cnp.api.apirestcnp.model.Contrat;
import com.cnp.api.apirestcnp.model.Inscription;
import com.cnp.api.apirestcnp.model.ReseauDistribution;
import com.cnp.api.apirestcnp.repository.InscriptionRepository;
import com.cnp.api.apirestcnp.service.InscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InscriptionServiceImpl implements InscriptionService {

    @Autowired
    InscriptionRepository inscriptionRepository;


    @Override
    public List<Inscription> getInscriptionByReseau(ReseauDistribution reseau) {
        return inscriptionRepository.findByReseauDistribution(reseau);
    }

    @Override
    public List<Inscription> getInscriptionByClient(Client client) {
        return inscriptionRepository.findByClient(client);
    }

    @Override
    public List<Inscription> getInscriptionByContrat(Contrat contrat) {
        return inscriptionRepository.findByContrat(contrat);
    }

    @Override
    public Inscription createInscription(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    @Override
    public Optional<Inscription> getInscriptionById(Integer idInscription) {
        return inscriptionRepository.findById(idInscription);
    }

    @Override
    public boolean deleteInscription(Integer idContrat) {
        boolean inscriptionDelete = true;
        Inscription inscription = inscriptionRepository.findById(idContrat).get();
        inscriptionRepository.delete(inscription);
        inscriptionDelete = (!inscriptionRepository.findById(idContrat).isPresent()) ? true : false;
        return inscriptionDelete;
    }

    @Override
    public List<Inscription> getInscriptionBetweenDateDebutAndDateFin(Date dateDebut, Date dateFin) {
        return inscriptionRepository.findByDateFinAndDateDebut(dateDebut, dateFin);
    }


}
