package com.cesi.apireservation.service;

import com.cesi.apireservation.dto.ReservationDTO;
import com.cesi.apireservation.model.Concert;
import com.cesi.apireservation.model.Reservation;
import com.cesi.apireservation.model.ReservationStatus;
import com.cesi.apireservation.model.UserApp;
import com.cesi.apireservation.repository.ConcertRepository;
import com.cesi.apireservation.repository.ReservationRepository;
import com.cesi.apireservation.repository.UserAppRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private UserAppService userAppService;

    private ModelMapper modelMapper;

    public ReservationService() {
        modelMapper = new ModelMapper();
    }

    @Transactional
    public ReservationDTO save(ReservationDTO reservationDTO, Long concertId) throws Exception {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        reservation.setReservationStatus(ReservationStatus.pending);
        UserApp userApp = userAppService.getUserFromToken();
        reservation.setUser(userApp);
        Concert concert = concertRepository.findById(concertId).get();
        if(concert == null) {
            throw new Exception("concert not found");
        }
        reservation.setConcert(concert);
        reservation = reservationRepository.save(reservation);
        if(reservation == null) {
            throw  new Exception("Error when reservation added to database");
            //Ajouter les logs : La méthode, l'heure, les arguments, context
            //Un service de log
        }
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    //Méthode pour récupérer les réservations

    public List<ReservationDTO> getAll() throws Exception {
        List<ReservationDTO> reservations= new ArrayList<>();
        UserApp userApp = userAppService.getUserFromToken();
        if(userApp.isAdmin()) {
            reservationRepository.findAll().forEach(r -> {
                reservations.add(modelMapper.map(r, ReservationDTO.class));
            });
        }else {
            reservationRepository.findAllByUser(userApp).forEach(r -> {
                reservations.add(modelMapper.map(r, ReservationDTO.class));
            });
        }
        return reservations;
    }

    //Méthode pour mettre à jour le status (uniqument pour l'admin)
    public void updateReservationAdmin(Long reservationId, ReservationStatus status) throws Exception {
        UserApp userApp = userAppService.getUserFromToken();
        /*if(userApp.isAdmin()) {
            Reservation reservation = reservationRepository.findById(reservationId).get();
            if(reservation == null) {
                throw  new Exception("Error when reservation added to database");
            }
            reservation.setReservationStatus(status);
            reservationRepository.save(reservation);
        }
        else {
            throw new Exception("Not allowed exception");
        }*/
        Reservation reservation = reservationRepository.findById(reservationId).get();
        if(reservation == null) {
            throw  new Exception("Error when reservation added to database");
        }
        reservation.setReservationStatus(status);
        reservationRepository.save(reservation);
    }

}
