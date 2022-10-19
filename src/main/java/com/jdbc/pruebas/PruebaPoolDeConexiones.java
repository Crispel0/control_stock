package com.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.jdbc.factory.ConexionFactory;

public class PruebaPoolDeConexiones {
	
	public static void main(String[] args) throws SQLException {
		ConexionFactory conexionfactory = new ConexionFactory();
		for (int i = 0; i < 20; i++) {
			
			Connection conexion = conexionfactory.RecuperaConexion();
			
			System.out.println("La cantidad de conexiones es " + (i + 1 ));
			
		}
	}

}
