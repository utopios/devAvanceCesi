package com.cesi.apireservation.dto;

import java.io.Serializable;

public class ReservationDTO implements Serializable {
    private Long id;
    private int nbPlace;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
}
