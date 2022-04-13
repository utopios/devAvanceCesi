package com.cnp.api.apirestcnp.repository;

import com.cnp.api.apirestcnp.model.ReseauDistribution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReseauDistributionRepository extends CrudRepository<ReseauDistribution, Integer> {

}
