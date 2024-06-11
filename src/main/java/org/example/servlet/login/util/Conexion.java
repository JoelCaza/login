package org.example.servlet.login.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Conexion {


    public static Connection getConnection() {

        Connection connexion = null;
        var baseDatos = "mydb";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "1234";
        //Cargamos el pool de conexiones al driver en memoria
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, usuario, password);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ocurrio un error al conectase a la base de datos"+e.getMessage());

        }
        return connexion;

    }

    public static void main (String[] args) {
        Connection connexion =getConnection();
        if (connexion == null) {
            System.out.println("Error al conectar a la base de datos");
        }
        else {
            System.out.println("Conexion Exitosa");
        }
    }
}
