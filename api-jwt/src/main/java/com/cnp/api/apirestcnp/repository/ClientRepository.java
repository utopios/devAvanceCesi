package com.cnp.api.apirestcnp.repository;

import com.cnp.api.apirestcnp.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {


}
