package com.stanstoynov;

public class TableRecordPopulatedPlaces {
    private int id;
    private String populatedPlace;

    public TableRecordPopulatedPlaces() {

    }

    public TableRecordPopulatedPlaces(int id, String populatedPlace) {
        this.id = id;
        this.populatedPlace = populatedPlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPopulatedPlace() {
        return populatedPlace;
    }

    public void setPopulatedPlace(String populatedPlace) {
        this.populatedPlace = populatedPlace;
    }

    @Override
    public String toString() {
        return populatedPlace;
    }
}