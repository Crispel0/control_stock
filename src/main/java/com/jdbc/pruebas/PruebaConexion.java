package com.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import com.jdbc.factory.ConexionFactory;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
        Connection con = new ConexionFactory().RecuperaConexion();
        
        System.out.println("Cerrando la conexión");

        con.close();
    }

}
