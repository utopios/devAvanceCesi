package com.cesi.apireservation.repository;

import com.cesi.apireservation.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
