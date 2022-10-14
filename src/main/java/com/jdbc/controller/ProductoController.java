package com.jdbc.controller;

import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.jdbc.factory.ConexionFactory;

public class ProductoController {

	public int modificar(String nombre, String descripcion,Integer cantidad, Integer id) throws SQLException {
		Connection con = new ConexionFactory().RecuperaConexion();
		Statement statement = con.createStatement();
		
		statement.execute("UPTADE FROM PRODUCTOS SET"
				+ "NOMBRE = '" + nombre + "'"
						+ ", DESCRIPCION = '" + descripcion + "'" 
						+ ", CANTIDAD = " + cantidad
						+ " ID = " + id);
		
		int updateacount = statement.getUpdateCount();
		
		con.close();
		
		return updateacount;
		
	}

	public int eliminar(Integer id) throws SQLException {
		
		Connection con= new ConexionFactory().RecuperaConexion();
		Statement statement = con.createStatement();
		statement.execute("DELETE FROM PRODUCTOS WHERE =" + id); //query para eliminar de la tabla de productos de acuerdo al id por parametro
		
		return statement.getUpdateCount(); //metodo para conocer cuantos registros son eliminados despues de una query returna un int
		
		
	}

	public List<Map<String, String>> listar() throws SQLException {
		
		//Conexion base de datos, creacion de query en java con Statement y ejecucion de query  para recuperar informacion de base datos//
		
        Connection con = new ConexionFactory().RecuperaConexion();
        
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

	//Metodo que toma un insert de la base de datos y lo asigna a cada parte columna del mismo y returna el id como clave generada del insert
    public void guardar(Map<String, String> producto) throws SQLException {
		Connection con = new ConexionFactory().RecuperaConexion();
		Statement statement = con.createStatement();
		statement.execute("INSERT INTO PRODUCTOS(NOMBRE, DESCRIPCION, CANTIDAD) " +
		"VALUES('" + producto.get("NOMBRE") + "' , '" +
				producto.get("DESCRIPCION") + "' , " + 
					producto.get("CANTIDAD") + ")" , statement.RETURN_GENERATED_KEYS);
		
		ResultSet resultset = statement.getGeneratedKeys(); //obtiene las llaves generadas que retornara//
		
		while(resultset.next()) {
			System.out.println(String.format("fue insertado el producto con ID %d", resultset.getInt(1)));
		}
		
	}

}
