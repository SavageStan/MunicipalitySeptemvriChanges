package com.stanstoynov;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stan.MessageBox;

public class AddRecordStageBuilder {

    private static Stage stage;
    private static TextField folderNumberTextField;
    private static ChoiceBox populatedPlaceChoiceBox;
    private static TextField districtTextField;
    private static TextArea parcelTextArea;
    private static ChoiceBox recordTypeChoiceBox;
    private static TextArea orderDecisionTextArea;
    private static TextArea descriptionTextArea;

    public static void showAddRecordStage(int selectedPopulatedPlaceIndex) {
        stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Добавяне на нов запис");
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.setSpacing(5);

        // Folder number
        Label folderNumberLabel = new Label("Папка №");
        folderNumberTextField = new TextField();

        // Populated place
        Label populatedPlaceLabel = new Label("Населено място");
        populatedPlaceChoiceBox = new ChoiceBox(SQLHandler.selectAllFromPopulatedPlaces());
        populatedPlaceChoiceBox.getItems().remove(15); // Remove "Всички"
        if(selectedPopulatedPlaceIndex != 15) {
            populatedPlaceChoiceBox.getSelectionModel().select(selectedPopulatedPlaceIndex);
        }
        else {
            populatedPlaceChoiceBox.getSelectionModel().select(0);
        }

        // District
        Label districtLabel = new Label("Квартал");
        districtTextField = new TextField();

        // Parcel
        Label parcelLabel = new Label("Парцел");
        parcelTextArea = new TextArea();
        parcelTextArea.setWrapText(true);
        parcelTextArea.setPrefHeight(50);

        // Record type
        Label recordTypeLabel = new Label("Тип");
        recordTypeChoiceBox = new ChoiceBox(SQLHandler.selectAllFromRecordTypes());
        recordTypeChoiceBox.getSelectionModel().select(0);

        // OrderDecision
        Label orderDecisionLabel = new Label("Заповед/решение (Забележка: винаги оставяй едно празно място след символа №)");
        orderDecisionTextArea = new TextArea();
        orderDecisionTextArea.setWrapText(true);
        orderDecisionTextArea.setPrefHeight(50);

        // Description
        Label descriptionLabel = new Label("Описание");
        descriptionTextArea = new TextArea();
        descriptionTextArea.setWrapText(true);
        descriptionTextArea.setPrefHeight(100);

        // Buttons
        Button okButton = new Button("Запис");
        okButton.setOnAction((e) -> addRecord());
        Button closeButton = new Button("Затвори");
        closeButton.setOnAction((e) -> stage.close());
        HBox buttonsHBox = new HBox();
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(10);
        buttonsHBox.getChildren().addAll(okButton, closeButton);

        vBox.getChildren().addAll(folderNumberLabel, folderNumberTextField,
                populatedPlaceLabel, populatedPlaceChoiceBox,
                districtLabel, districtTextField,
                parcelLabel, parcelTextArea,
                recordTypeLabel, recordTypeChoiceBox,
                orderDecisionLabel, orderDecisionTextArea,
                descriptionLabel, descriptionTextArea,
                buttonsHBox);
        stage.setScene(new Scene(vBox));
        stage.showAndWait();
    }

    private static void addRecord()
    {
        // TODO: 20-Oct-18 make input validation here
    }
}

// TODO: 20-Oct-18 call the table view refresh method upon a successful record insert.
// TODO: 20-Oct-18 if the user has selected a different populated place to insert a record into,
// TODO: 20-Oct-18 make sure to also swap the populated place for the main table to display the newly created record.