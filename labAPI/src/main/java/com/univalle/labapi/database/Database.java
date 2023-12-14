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
 * Clase Database para manejar la conexión con una base de datos MariaDB.
 * Esta clase proporciona métodos para iniciar y cerrar la conexión con la base de datos.
 * Utiliza JDBC para conectarse a MariaDB.
 * 
 * @author Juan Camilo Chavez Castro
 * @author Juan Montenegro
 * @author Juan David Beltrán Castaño
 */
public class Database {
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://localhost/db2023-2";
    private Connection connection = null;
    private final Properties props;
    
    /**
     * Constructor de la clase Database.
     * Inicializa las propiedades de conexión con el usuario y la contraseña proporcionados.
     * 
     * @param user     Nombre de usuario para la conexión a la base de datos.
     * @param password Contraseña para la conexión a la base de datos.
     */
    public Database(String user, String password) {
        props = new Properties();
        props.put("user", user);
        props.put("password", password);
    }
   
    /**
     * Inicializa la conexión a la base de datos.
     * Carga el driver de JDBC y establece la conexión con las propiedades definidas.
     * 
     * @throws SQLException Si ocurre un error al establecer la conexión con la base de datos.
     */
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
    
    /**
     * Cierra la conexión a la base de datos.
     * 
     * @throws SQLException Si ocurre un error al cerrar la conexión con la base de datos.
     */
    public void closeConnection() throws SQLException {
        System.out.println("Closing database connection...");
        connection.close();
        System.out.println("Connection valid: " + connection.isValid(5));
    }
    
    /**
     * Comprueba si la conexión actual a la base de datos es válida.
     * 
     * @return true si la conexión es válida, false en caso contrario.
     * @throws SQLException Si ocurre un error al verificar el estado de la conexión.
     */
    
    public Boolean isConnectionValid() throws SQLException {
        return connection.isValid(5);
    }
    
    /**
     * Obtiene la conexión actual a la base de datos.
     * 
     * @return La conexión actual a la base de datos.
     */
    public Connection getConnection(){
        return this.connection;
    }
}
