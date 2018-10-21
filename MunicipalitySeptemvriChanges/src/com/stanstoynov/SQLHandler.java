package com.stanstoynov;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

public class SQLHandler {

    public static ObservableList<TableRecordPlanChanges> selectAllFromPlanChanges(TableRecordPopulatedPlaces currentPopulatedPlace) {
        // Create a new data structure.
        ObservableList<TableRecordPlanChanges> tableDataStructure = FXCollections.observableArrayList();

        // Create temp variables.
        int tempId;
        int tempFolderNumber;
        String tempPopulatedPlace;
        String tempDistrict;
        String tempParcel;
        String tempRecordType;
        String tempOrderDecision;
        String tempDescription;
        java.sql.Date tempDateOfEntry;

        // Prepare the SQL.
        String whereClause = "";

        if(!(currentPopulatedPlace.getId() == 16)) { // id 16, name Всички
            whereClause = " WHERE planChanges.populatedPlaceId = " + currentPopulatedPlace.getId();
        }

        String sql =  "SELECT " +
                      "planChanges.id, " +
                      "planChanges.folderNumber, " +
                      "populatedPlaces.name, " +
                      "planChanges.district, " +
                      "planChanges.parcel, " +
                      "recordTypes.type," +
                      "planChanges.orderDecision, " +
                      "planChanges.description," +
                      "planChanges.dateOfEntry\n" +
                      "FROM planChanges\n" +
                      "JOIN populatedPlaces ON planChanges.populatedPlaceId = populatedPlaces.id\n" +
                      "JOIN recordTypes ON planChanges.recordTypeId = recordTypes.id" + whereClause + ";";

        // Collect data from the database (by executing the SQL query).
        // Get the data from the returned result set and place it inside the temp variables.
        // Use the temp variables to create TableRecordPlanChanges objects (for each record).
        // Place each TableRecordPlanChanges object in the data structure (ObservableList).
        // Iterate over the result set until you run out of records.
        try {
            PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                tempId = resultSet.getInt(1);
                tempFolderNumber = resultSet.getInt(2);
                tempPopulatedPlace = resultSet.getString(3);
                tempDistrict = resultSet.getString(4);
                tempParcel = resultSet.getString(5);
                tempRecordType = resultSet.getString(6);
                tempOrderDecision = resultSet.getString(7);
                tempDescription = resultSet.getString(8);
                tempDateOfEntry = SQLHandler.convertStringToJavaSQLDate(resultSet.getString(9));

                tableDataStructure.add(new TableRecordPlanChanges(tempId, tempFolderNumber, tempPopulatedPlace,
                        tempDistrict, tempParcel, tempRecordType, tempOrderDecision, tempDescription, tempDateOfEntry));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tableDataStructure;
    }

    public static ObservableList<TableRecordPopulatedPlaces> selectAllFromPopulatedPlaces() {
        ObservableList<TableRecordPopulatedPlaces> choiceBoxDataStructure = FXCollections.observableArrayList();
        int tempId;
        String tempPopulatedPlace;

        String sql =  "SELECT " +
                      "populatedPlaces.id, " +
                      "populatedPlaces.name " +
                      "FROM populatedPlaces;";
        try {
            PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                tempId = resultSet.getInt(1);
                tempPopulatedPlace = resultSet.getString(2);
                choiceBoxDataStructure.add(new TableRecordPopulatedPlaces(tempId, tempPopulatedPlace));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return choiceBoxDataStructure;
    }

    public static ObservableList<TableRecordRecordTypes> selectAllFromRecordTypes() {
        ObservableList<TableRecordRecordTypes> choiceBoxDataStructure = FXCollections.observableArrayList();
        int tempId;
        String tempRecordType;

        String sql =  "SELECT " +
                "recordTypes.id, " +
                "recordTypes.type " +
                "FROM recordTypes;";
        try {
            PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                tempId = resultSet.getInt(1);
                tempRecordType = resultSet.getString(2);
                choiceBoxDataStructure.add(new TableRecordRecordTypes(tempId, tempRecordType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return choiceBoxDataStructure;
    }

    public static void insertIntoPlanChanges()
    {
        // TODO: 20-Oct-18 make insert query here 
    }

    public static java.sql.Date convertStringToJavaSQLDate(String dateString) {
        int yearInt = Integer.parseInt(dateString.substring(0, 4));
        int monthInt = Integer.parseInt(dateString.substring(5, 7));
        int dayInt = Integer.parseInt(dateString.substring(8));

        Calendar calendar = Calendar.getInstance();
        calendar.set(yearInt, monthInt-1, dayInt);

        return new java.sql.Date(calendar.getTimeInMillis());
    }

    public static String constructURL(String databaseFileName) {
        String url = "jdbc:sqlite:";
        String absolutePath = new File("").getAbsoluteFile().toString();
        absolutePath = absolutePath.concat("\\db\\");
        absolutePath = absolutePath.concat(databaseFileName);
        url = url.concat(absolutePath);
        System.out.println(url);
        return url;
    }
}