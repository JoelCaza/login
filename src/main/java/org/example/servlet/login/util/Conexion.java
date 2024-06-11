package org.example.servlet.login.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Conexion {
    private static String url = "jdbc://localhost:3306/mydb?serverTimezone=America/Quito";
    private static String usuario = "root";
    private static String password = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }
}
