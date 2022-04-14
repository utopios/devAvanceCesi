package com.cesi.apireservation.repository;

import com.cesi.apireservation.model.Concert;
import org.springframework.data.repository.CrudRepository;

public interface ConcertRepository extends CrudRepository<Concert, Long> {
}
