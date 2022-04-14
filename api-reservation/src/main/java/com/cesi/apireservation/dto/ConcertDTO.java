package com.cesi.apireservation.dto;

import java.util.Date;

public class ConcertDTO {

    private Long id;
    private String title;
    private Date date;
    private int placeMax;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPlaceMax() {
        return placeMax;
    }

    public void setPlaceMax(int placeMax) {
        this.placeMax = placeMax;
    }
}
