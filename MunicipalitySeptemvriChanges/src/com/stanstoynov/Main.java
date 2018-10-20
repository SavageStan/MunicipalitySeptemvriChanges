package com.stanstoynov;

import javafx.application.Application;
import javafx.stage.Stage;
import stan.ConfirmationBox;

public class Main extends Application {
    @Override
    public void init() {
        // TODO: 16-Oct-18 File locking does not work! Pretty sure it will work even less when inside a jar rofl.
        //FileLockChecker.checkForOtherInstance();
        DatabaseConnector.establishConnection(SQLHandler.constructURL("municipalitySeptemvri.db"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //primaryStage.setWidth(1200);
        //primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.setScene(MainSceneBuilder.buildScene());
        primaryStage.setTitle("Община Септември изменения");
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("javafx stop");
        DatabaseConnector.closeConnection();

        // TODO: 18-Oct-18 change the behaviour of the X button somehow, it has to call the closeApplication() method 
        
        //closeApplication();
    }

    public static void closeApplication() {
        int value = ConfirmationBox.showConfirmationBox("Да", "Не", "Потвърждение", "Сигурни ли сте, че искате да затворите програмата?");
        if(value == 0) {
            DatabaseConnector.closeConnection();
            System.exit(0);
        }

        // TODO: 18-Oct-18 confirmation box does not show up when the app has been started from the jar!!!! 
    }

    public static void main(String[] args) {
        // The main method is basically skipped in JavaFX programs.
        // This launch(args) method call is just for cases in which
        // the standard JavaFX lifecycle does not start as normal.
        // It this case, this just starts the JavaFX lifecycle.
        launch(args);
    }
}
