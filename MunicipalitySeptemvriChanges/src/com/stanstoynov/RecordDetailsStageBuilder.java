package com.stanstoynov;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stan.MessageBox;

public class RecordDetailsStageBuilder {

    public static void showRecordDetailsStage(TableRecordPlanChanges currentlySelectedRecord) {
        if(currentlySelectedRecord == null) {
            MessageBox.showMessageBox("Грешка!", "Не е избран запис!\n\n");
            return;
        }

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Детайли за запис");
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.setSpacing(5);

        // Folder number
        Label folderNumberLabel = new Label("Папка №");
        TextField folderNumberTextField = new TextField();
        folderNumberTextField.setText(Integer.toString(currentlySelectedRecord.getFolderNumber()));
        folderNumberTextField.setEditable(false);

        // Populated place
        Label populatedPlaceLabel = new Label("Населено място");
        TextField populatedPlaceTextField = new TextField();
        populatedPlaceTextField.setText(currentlySelectedRecord.getPopulatedPlace());
        populatedPlaceTextField.setEditable(false);

        // District
        Label districtLabel = new Label("Квартал");
        TextField districtTextField = new TextField();
        districtTextField.setText(currentlySelectedRecord.getDistrict());
        districtTextField.setEditable(false);

        // Parcel
        Label parcelLabel = new Label("Парцел");
        TextArea parcelTextArea = new TextArea();
        parcelTextArea.setText(currentlySelectedRecord.getParcel());
        parcelTextArea.setEditable(false);
        parcelTextArea.setWrapText(true);
        parcelTextArea.setPrefHeight(50);

        // Record type
        Label recordTypeLabel = new Label("Тип");
        TextField recordTypeTextField = new TextField();
        recordTypeTextField.setText(currentlySelectedRecord.getRecordType());
        recordTypeTextField.setEditable(false);

        // OrderDecision
        Label orderDecisionLabel = new Label("Заповед/решение");
        TextArea orderDecisionTextArea = new TextArea();
        orderDecisionTextArea.setText(currentlySelectedRecord.getOrderDecision());
        orderDecisionTextArea.setEditable(false);
        orderDecisionTextArea.setWrapText(true);
        orderDecisionTextArea.setPrefHeight(50);

        // Description
        Label descriptionLabel = new Label("Описание");
        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setText(currentlySelectedRecord.getDescription());
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setWrapText(true);
        descriptionTextArea.setPrefHeight(100);

        // Button
        Button closeButton = new Button("Затвори");
        closeButton.setOnAction((e) -> stage.close());
        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.getChildren().addAll(closeButton);

        vBox.getChildren().addAll(folderNumberLabel, folderNumberTextField,
                populatedPlaceLabel, populatedPlaceTextField,
                districtLabel, districtTextField,
                parcelLabel, parcelTextArea,
                recordTypeLabel, recordTypeTextField,
                orderDecisionLabel, orderDecisionTextArea,
                descriptionLabel, descriptionTextArea,
                buttonHBox);
        stage.setScene(new Scene(vBox));
        stage.showAndWait();

        // TODO: 20-Oct-18 request focus does not work, even after showing the stage. wtf?!?!
        closeButton.requestFocus();
    }
}
