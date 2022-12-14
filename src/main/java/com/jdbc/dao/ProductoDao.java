package com.jdbc.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdbc.factory.ConexionFactory;
import com.jdbc.modelo.Producto;

//DAO DataAccesObject se encarga de manejar todos los datos de un objeto haciendo mas autoindependiente//

public class ProductoDao{
	
	final Connection conexion;
	
	public ProductoDao(Connection conexion) {
		this.conexion = conexion;
	}
	
	public void guardarProducto(Producto producto) throws SQLException {
		
		
		try(conexion){
		
		//conexionfigura para que la conexionexion no este a cargo de guardar los datos en caso de error//
		
		
		// convierte a variables de su tipo cada llamada de la tabla de base de datos//
		String nombre = producto.getNombre();
		String descripcion = producto.getDescripcion();
		Integer cantidad = producto.getCantidad();
		
		final PreparedStatement statement = conexion.prepareStatement("INSERT INTO PRODUCTOS(NOMBRE, DESCRIPCION"
				+ "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS ); 
		
		try(statement){
			try {
				
			do {
				
			//el producto tiene todas valores de nombre descripcion y cantidad//
			ejecutarRegistro(producto,statement);
			
			}while(cantidad > 0);
			
			
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
			
		}
	}
}

	private void ejecutarRegistro(Producto producto, PreparedStatement statement) throws SQLException {
	
		
		//Asignamiento de statement a tipo de datos conexion sus columnas//
		statement.setString(1, producto.getNombre());
		statement.setString(2, producto.getDescripcion());
		statement.setInt(3, producto.getCantidad());
		
		final ResultSet resultset = statement.getGeneratedKeys();
		try(resultset){
			
			while(resultset.next()) {
				System.out.println(String.format("Fue registrado un %d", producto.toString()));
		}
		}
	}

	public static List<Producto> listar() throws SQLException {
//Conexion base de datos, creacion de query en java con Statement y ejecucion de query  para recuperar informacion de base datos//
		
        final Connection conexion = new ConexionFactory().RecuperaConexion();
        
        try(conexion){
        
		final PreparedStatement statement = conexion.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTOS");
		statement.execute();
		
		try(statement){
		
		ResultSet resultset =  statement.getResultSet();
		
		// Aqui pongo un Map porque no hay un objeto especifico para devolver//
		List<Producto> resultado = new ArrayList<>();
		
		//cada tipo resultset lo recorremos tomamos su valor y lo guardamos en un Map al final lo agregamos a resultado//
		while(resultset.next()) {
			
			Producto fila = new Producto(resultset.getInt("ID"), resultset.getString("NOMBRE"), resultset.getString("DESCRIPCION"), (resultset.getInt("CANTIDAD")));
			resultado.add(fila);
			
		}
					
		return resultado;
		}
        }
	}
	}



