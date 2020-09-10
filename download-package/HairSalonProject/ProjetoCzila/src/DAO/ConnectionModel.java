package DAO;

import java.sql.*;

public class ConnectionModel {

    //method responsible for connection with the database
    public static Connection conector() {

        java.sql.Connection connection = null;

        String driver = "com.mysql.jdbc.Driver";

        //Path to the database
        String url = "jdbc:mysql://localhost:3306/dbcezila";
        String user = "root";
        String password = "";
        
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
}