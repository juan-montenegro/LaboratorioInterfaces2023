/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author juane
 */
public class Database {
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://localhost/db2023-2";
    private Connection connection;
    private final Properties props;

    public Database(String user, String password) {
        props = new Properties();
        props.put("user", user);
        props.put("password", password);
    }
    
    public void initConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se  puede cargar el Driver de MYSQL");
        }
        System.out.println("Connecting to the database...");
        connection = DriverManager.getConnection(URL, props);
        System.out.println("Connection valid: " + connection.isValid(5));

    }
    
    public void closeConnection() throws SQLException {
        System.out.println("Closing database connection...");
        connection.close();
        System.out.println("Connection valid: " + connection.isValid(5));
    }
    
    public Boolean isConnectionValid() throws SQLException {
        return connection.isValid(5);
    }
    
}
