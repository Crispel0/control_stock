package com.jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdbc.CreaConexion;

public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<Map<String, String>> listar() throws SQLException {
		
		//Conexion base de datos, creacion de query en java con Statement y ejecucion de query  para recuperar informacion de base datos//
		
        Connection con = new CreaConexion().RecuperaConexion();
        
		Statement statement = con.createStatement();
		
		statement.execute("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTOS");
		ResultSet resultset =  statement.getResultSet();
		
		// Aqui pongo un Map porque no hay un objeto especifico para devolver//
		
		List<Map<String, String>> resultado = new ArrayList<>();
		
		//cada tipo resultset lo recorremos tomamos su valor y lo guardamos en un Map al final lo agregamos a resultado//
		while(resultset.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("ID", String.valueOf(resultset.getInt("ID"))); //Toma como llave el Id y valor ID de base Datos se debe colocar StringvalueOf porque no acepta int para convertirlo a String//
			fila.put("NOMBRE", resultset.getString("NOMBRE"));
			fila.put("DESCRIPCION", resultset.getString("DESCRIPCION"));
			fila.put("CANTIDAD", String.valueOf(resultset.getInt("CANTIDAD")));
			
			resultado.add(fila);
			
		}
	
		con.close();
				
		return resultado;
	}

    public void guardar(Object producto) {
		// TODO
	}

}
