package com.cesi.apireservation.model;

import javax.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int nbPlace;

    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserApp user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public UserApp getUser() {
        return user;
    }

    public void setUser(UserApp user) {
        this.user = user;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}

enum ReservationStatus {
    pending,
    valid,
    cancel
}
