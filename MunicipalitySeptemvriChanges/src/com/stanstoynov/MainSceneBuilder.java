package com.stanstoynov;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.InputStream;

public class MainSceneBuilder {
    private static TableView<TableRecordPlanChanges> tableView;
    private static TableColumn<TableRecordPlanChanges, Integer> idColumn = new TableColumn<>("ID");
    private static TableColumn<TableRecordPlanChanges, Integer> folderNumberColumn = new TableColumn<>("Папка\n№");
    private static TableColumn<TableRecordPlanChanges, String> populatedPlaceColumn = new TableColumn<>("Населено\nмясто");
    private static TableColumn<TableRecordPlanChanges, String> districtColumn = new TableColumn<>("Квартал");
    private static TableColumn<TableRecordPlanChanges, String> parcelColumn = new TableColumn<>("Парцел");
    private static TableColumn<TableRecordPlanChanges, String> recordTypeColumn = new TableColumn<>("Тип");
    private static TableColumn<TableRecordPlanChanges, String> orderDecisionColumn = new TableColumn<>("Заповед/\nрешение");
    private static TableColumn<TableRecordPlanChanges, String> descriptionColumn = new TableColumn<>("Описание на изменение");
    private static TableColumn<TableRecordPlanChanges, java.sql.Date> dateOfEntryColumn = new TableColumn<>("Дата запис");

    private static ChoiceBox populatedPlaceChoiceBox;
    private static TableRecordPopulatedPlaces currentPopulatedPlace;

    private static TableRecordPlanChanges currentPlanChange;

    public static Scene buildScene() {
        BorderPane windowContent = new BorderPane();
        windowContent.setTop(buildTop());
        windowContent.setCenter(buildCenter());
        //windowContent.setBottom(buildBottom());

        VBox root = new VBox(MenuBarBuilder.buildMenuBar(), windowContent);
        return new Scene(root);
    }

    private static HBox buildTop() {
        HBox top = new HBox();
        top.setPadding(new Insets(5,5,0,5));
        top.setSpacing(5);
        top.setAlignment(Pos.CENTER);

        populatedPlaceChoiceBox = new ChoiceBox(SQLHandler.selectAllFromPopulatedPlaces());
        populatedPlaceChoiceBox.getSelectionModel().select(0);

        currentPopulatedPlace = (TableRecordPopulatedPlaces)populatedPlaceChoiceBox.getSelectionModel().getSelectedItem();
        populatedPlaceChoiceBox.setOnAction(event -> populatedPlaceChanged());

        top.getChildren().addAll(populatedPlaceChoiceBox);
        return top;
    }

    private static HBox buildCenter() {
        HBox center = new HBox();
        center.setPadding(new Insets(5));
        center.setSpacing(5);

        tableView = new TableView<>();
        //tableView.setPrefHeight(500);
        tableView.setPlaceholder(new Label("Няма записи за това населено място!"));
        refreshTableView(currentPopulatedPlace);

        idColumn.setPrefWidth(50);
        folderNumberColumn.setPrefWidth(50);
        populatedPlaceColumn.setPrefWidth(100);
        districtColumn.setPrefWidth(100);
        parcelColumn.setPrefWidth(200);
        recordTypeColumn.setPrefWidth(60);
        orderDecisionColumn.setPrefWidth(125);
        descriptionColumn.setPrefWidth(425);
        dateOfEntryColumn.setPrefWidth(100);

        center.getChildren().addAll(tableView, buildCRUDButtons());
        return center;
    }

    private static VBox buildCRUDButtons() {
        // Load images from the folder marked as "Resource root".
        // For more info, read the tips in the "how to access a file as a resource.txt" file.
        Image searchImage = null;
        Image detailsImage = null;
        Image addImage = null;
        Image editImage = null;
        Image removeImage = null;

        try {
            InputStream inputStream = MainSceneBuilder.class.getClassLoader().getResourceAsStream("images/search_blue_64x64.png");
            searchImage = new Image(inputStream);

            inputStream = MainSceneBuilder.class.getClassLoader().getResourceAsStream("images/check_blue_64x64.png");
            detailsImage = new Image(inputStream);

            inputStream = MainSceneBuilder.class.getClassLoader().getResourceAsStream("images/add_green_64x64.png");
            addImage = new Image(inputStream);

            inputStream = MainSceneBuilder.class.getClassLoader().getResourceAsStream("images/edit_blue_64x64.png");
            editImage = new Image(inputStream);

            inputStream = MainSceneBuilder.class.getClassLoader().getResourceAsStream("images/remove_red_64x64.png");
            removeImage = new Image(inputStream);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // Create ImageView objects.
        ImageView searchImageView = new ImageView(searchImage);
        ImageView detailsImageView = new ImageView(detailsImage);
        ImageView addImageView = new ImageView(addImage);
        ImageView editImageView = new ImageView(editImage);
        ImageView removeImageView = new ImageView(removeImage);

        // Create buttons (with images).
        Button searchButton = new Button("Търсене", searchImageView);
        Button showDetailsButton = new Button("Детайли", detailsImageView);
        Button addButton = new Button("Добавяне", addImageView);
        Button editButton = new Button("Редакция", editImageView);
        Button deleteButton = new Button("Изтриване", removeImageView);

        // Set alignment of the contents of each button.
        searchButton.setAlignment(Pos.CENTER_LEFT);
        showDetailsButton.setAlignment(Pos.CENTER_LEFT);
        addButton.setAlignment(Pos.CENTER_LEFT);
        editButton.setAlignment(Pos.CENTER_LEFT);
        deleteButton.setAlignment(Pos.CENTER_LEFT);

        // Set button font.
        searchButton.setStyle("-fx-font-size:15");
        searchButton.setStyle("-fx-font-weight: bold");
        showDetailsButton.setStyle("-fx-font-size:15");
        showDetailsButton.setStyle("-fx-font-weight: bold");
        addButton.setStyle("-fx-font-size:15");
        addButton.setStyle("-fx-font-weight: bold");
        editButton.setStyle("-fx-font-size:15");
        editButton.setStyle("-fx-font-weight: bold");
        deleteButton.setStyle("-fx-font-size:15");
        deleteButton.setStyle("-fx-font-weight: bold");

        // Set the width of the buttons.
        int width = 150;;
        searchButton.setMinWidth(width);
        searchButton.setPrefWidth(width);
        searchButton.setMaxWidth(width);
        showDetailsButton.setMinWidth(width);
        showDetailsButton.setPrefWidth(width);
        showDetailsButton.setMaxWidth(width);
        addButton.setMinWidth(width);
        addButton.setPrefWidth(width);
        addButton.setMaxWidth(width);
        editButton.setMinWidth(width);
        editButton.setPrefWidth(width);
        editButton.setMaxWidth(width);
        deleteButton.setMinWidth(width);
        deleteButton.setPrefWidth(width);
        deleteButton.setMaxWidth(width);

        // Set tooltips for the buttons.
        searchButton.setTooltip(new Tooltip("Търсене на запис."));
        showDetailsButton.setTooltip(new Tooltip("Покажи избрания запис в детайли."));
        addButton.setTooltip(new Tooltip("Добави нов запис."));
        editButton.setTooltip(new Tooltip("Редактирай избрания запис."));
        deleteButton.setTooltip(new Tooltip("Изтрий избрания запис."));

        // Set action listeners for the buttons.
        searchButton.setOnAction(event -> searchButtonClicked());
        showDetailsButton.setOnAction(event -> showDetailsButtonClicked());
        addButton.setOnAction(event -> addButtonClicked());
        editButton.setOnAction(event -> editButtonClicked());
        deleteButton.setOnAction(event -> deleteButtonClicked());

        // Add the buttons to a VBox and return it to the method caller.
        VBox crudButtonsVBox = new VBox();
        crudButtonsVBox.getChildren().addAll(searchButton, showDetailsButton, addButton, editButton, deleteButton);
        crudButtonsVBox.setSpacing(5);
        return crudButtonsVBox;
    }

    private static void searchButtonClicked() {
        System.out.println("search clicked");
    }

    private static void showDetailsButtonClicked() {
        RecordDetailsStageBuilder.showRecordDetailsStage(tableView.getSelectionModel().getSelectedItem());
    }

    private static void addButtonClicked() {
        AddRecordStageBuilder.showAddRecordStage(populatedPlaceChoiceBox.getSelectionModel().getSelectedIndex());
    }

    private static void editButtonClicked() {
        System.out.println("edit clicked");
    }

    private static void deleteButtonClicked() {
        System.out.println("delete clicked");
    }

    private static void populatedPlaceChanged() {
        currentPopulatedPlace = (TableRecordPopulatedPlaces) populatedPlaceChoiceBox.getSelectionModel().getSelectedItem();
        refreshTableView(currentPopulatedPlace);
    }

    private static TableRecordPopulatedPlaces getCurrentPopulatedPlace() {
        return currentPopulatedPlace;
    }

    public static void setCurrentPopulatedPlace(TableRecordPopulatedPlaces currentPopulatedPlace) {
        MainSceneBuilder.currentPopulatedPlace = currentPopulatedPlace;
    }

    /**
     * This method gets a current snapshot of the contents of the planChanges table,
     * joined with data from other two tables, and places it in the tableView.
     */
    private static void refreshTableView(TableRecordPopulatedPlaces currentPopulatedPlace)
    {
        // Remove any existing data structure from the table.
        tableView.getItems().clear();
        // Remove any existing columns from the table.
        tableView.getColumns().clear();

        // Set the underlying data structure for the table.
        tableView.setItems(SQLHandler.selectAllFromPlanChanges(currentPopulatedPlace));

        // Add the TableColumn objects to the table.
        tableView.getColumns().addAll(idColumn, folderNumberColumn, populatedPlaceColumn,
                districtColumn, parcelColumn, recordTypeColumn,
                orderDecisionColumn, descriptionColumn, dateOfEntryColumn);

        // Bind the table data structure with the columns. It makes the connection by object data field name.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        folderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("folderNumber"));
        populatedPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("populatedPlace"));
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));
        parcelColumn.setCellValueFactory(new PropertyValueFactory<>("parcel"));
        recordTypeColumn.setCellValueFactory(new PropertyValueFactory<>("recordType"));
        orderDecisionColumn.setCellValueFactory(new PropertyValueFactory<>("orderDecision"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateOfEntryColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfEntry"));

        // Scroll the tableView to the bottom.
        tableView.scrollTo(tableView.getItems().size());

        // Select the last record.
        tableView.getSelectionModel().select(tableView.getItems().size()-1);
    }
}