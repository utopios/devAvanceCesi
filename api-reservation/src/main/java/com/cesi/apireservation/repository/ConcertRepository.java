package com.cesi.apireservation.repository;

import com.cesi.apireservation.model.Concert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends CrudRepository<Concert, Long> {

}
