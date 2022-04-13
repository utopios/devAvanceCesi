package com.cnp.api.apirestcnp.repository;

import com.cnp.api.apirestcnp.model.Contrat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContratRepository extends CrudRepository<Contrat, Integer> {


}
