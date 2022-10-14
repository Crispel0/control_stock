package com.jdbc.pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jdbc.CreaConexion;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
        Connection con = new CreaConexion().RecuperaConexion();
        
        System.out.println("Cerrando la conexión");

        con.close();
    }

}
