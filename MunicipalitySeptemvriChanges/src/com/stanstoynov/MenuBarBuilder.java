package com.stanstoynov;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import stan.ConfirmationBox;
import stan.MessageBox;

public class MenuBarBuilder {
    public static MenuBar buildMenuBar()
    {
        Menu fileMenu = new Menu("Файл");
        Menu helpMenu = new Menu("Помощ");

        MenuItem exitMenuItem = new MenuItem("Изход");
        exitMenuItem.setOnAction(event -> Main.closeApplication());
        fileMenu.getItems().add(exitMenuItem);

        MenuItem aboutMenuItem = new MenuItem("За програмата");
        aboutMenuItem.setOnAction(event -> showAbout());
        helpMenu.getItems().add(aboutMenuItem);

        return new MenuBar(fileMenu, helpMenu);
    }

    private static void showAbout() {
        MessageBox.showMessageBox("За програмата", "Oбщина Септември изменения\n" +
                "Версия: ver 1.00\n\n" +
                "Създадел: Станислав Атанасов Стойнов\n" +
                "e-mail: stanstoynov93@gmail.com\n" +
                "тел.: 089 692 2580\n\n" +
                "2018");
    }
}
