package com.stanstoynov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static Connection connection = null;

    public static boolean establishConnection(String url) {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            //MessageBox.showMessageBox("EXCEPTION", e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            //MessageBox.showMessageBox("EXCEPTION", e.getMessage());
            return false;
        }
        return true;    
    }

    public static boolean isConnectionEstablished() {
        if(connection == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public static Connection getConnection() {
          return connection;
    }
}
