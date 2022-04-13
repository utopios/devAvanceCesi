package com.cnp.api.apirestcnp.service;

import com.cnp.api.apirestcnp.model.Client;
import com.cnp.api.apirestcnp.model.Contrat;
import com.cnp.api.apirestcnp.model.Inscription;
import com.cnp.api.apirestcnp.model.ReseauDistribution;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InscriptionService {

    public List<Inscription> getInscriptionByReseau(ReseauDistribution reseau);

    public List<Inscription> getInscriptionByClient(Client client);

    public List<Inscription> getInscriptionByContrat(Contrat contrat);

    public Inscription createInscription(Inscription inscription);

    public Optional<Inscription> getInscriptionById(Integer idInscription);

    public boolean deleteInscription(Integer idInscription);

    public List<Inscription> getInscriptionBetweenDateDebutAndDateFin(Date dateDebut, Date dateFin);

}
