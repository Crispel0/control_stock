package com.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.factory.ConexionFactory;

public class PruebaEliminar {
	
	//Toma una Conexion, crea un statement, ejecuta una query y hace un conteo de lo que elimino si returna 0 es falso no enontro nada//
	
	public static void main(String[] args) throws SQLException { 
		
		Connection con = new ConexionFactory().RecuperaConexion();
		Statement statement = con.createStatement();
		
		statement.execute("DELETE FROM PRODUCTOS WHERE ID = 99");
		
		System.out.println(statement.getUpdateCount());
		
		
	}

}
