package com.cesi.apireservation.controller;

import com.cesi.apireservation.dto.ReservationDTO;
import com.cesi.apireservation.model.ReservationStatus;
import com.cesi.apireservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("")
    public ResponseEntity<?> getReservations() {
        try {
            List reservations = reservationService.getAll();
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("concert/{concertId}")
    public ResponseEntity<?> addReservation(@PathVariable Long concertId, @RequestBody ReservationDTO reservationDTO) {
        try {
            ReservationDTO reservation = reservationService.save(reservationDTO, concertId);
            return ResponseEntity.ok(reservation);
        }catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @PatchMapping("{reservationId}/status/{status}")
    public ResponseEntity<?> updateReservationStatus(@PathVariable Long reservationId, @PathVariable String status) {
        try {
            reservationService.updateReservationAdmin(reservationId, ReservationStatus.valueOf(status));
            return ResponseEntity.ok("update Ok");
        }catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }
}
