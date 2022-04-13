package com.cnp.api.apirestcnp.repository;

import com.cnp.api.apirestcnp.model.Client;
import com.cnp.api.apirestcnp.model.Contrat;
import com.cnp.api.apirestcnp.model.Inscription;

import com.cnp.api.apirestcnp.model.ReseauDistribution;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface InscriptionRepository extends CrudRepository<Inscription, Integer> {


    @Query("select i FROM Inscription i where i.reseauDistribution =:reseau")
    List<Inscription> findByReseauDistribution(@Param("reseau") ReseauDistribution reseau);

    @Query("select i FROM Inscription i where i.client =:client")
    List<Inscription> findByClient(@Param("client") Client client);

    @Query("select i FROM Inscription i where i.contrat =:contrat")
    List<Inscription> findByContrat(@Param("contrat") Contrat contrat);

    @Query("select i FROM Inscription i where i.dateDebut =>:dateDebut and i.dateFin =<:dateFin")
    List<Inscription> findByDateFinAndDateDebut(@Param("dateDebut") Date dateDebut, @Param("dateFIn") Date dateFin);


}
