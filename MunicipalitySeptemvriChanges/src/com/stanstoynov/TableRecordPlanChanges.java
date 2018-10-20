package com.stanstoynov;

public class TableRecordPlanChanges {
    private int id;
    private int folderNumber;
    private String populatedPlace;
    private String district;
    private String parcel;
    private String recordType;
    private String orderDecision;
    private String description;
    private java.sql.Date dateOfEntry;

    public TableRecordPlanChanges() {

    }

    public TableRecordPlanChanges(int id, int folderNumber, String populatedPlace,
                                  String district, String parcel, String recordType,
                                  String orderDecision, String description, java.sql.Date dateOfEntry) {
        this.id = id;
        this.folderNumber = folderNumber;
        this.populatedPlace = populatedPlace;
        this.district = district;
        this.parcel = parcel;
        this.recordType = recordType;
        this.orderDecision = orderDecision;
        this.description = description;
        this.dateOfEntry = dateOfEntry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFolderNumber() {
        return folderNumber;
    }

    public void setFolderNumber(int folderNumber) {
        this.folderNumber = folderNumber;
    }

    public String getPopulatedPlace() {
        return populatedPlace;
    }

    public void setPopulatedPlace(String populatedPlace) {
        this.populatedPlace = populatedPlace;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getOrderDecision() {
        return orderDecision;
    }

    public void setOrderDecision(String orderDecision) {
        this.orderDecision = orderDecision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(java.sql.Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }
}
