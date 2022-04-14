package com.cesi.apireservation.repository;

import com.cesi.apireservation.model.Concert;
import com.cesi.apireservation.model.Reservation;
import com.cesi.apireservation.model.UserApp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findAllByUser(UserApp userApp);
}
