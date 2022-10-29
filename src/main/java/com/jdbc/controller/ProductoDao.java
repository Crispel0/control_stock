package com.jdbc.controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.factory.ConexionFactory;
import com.jdbc.modelo.Categoria;
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
		
			String nombre = producto.getNombre();
			String descripcion = producto.getDescripcion();
			Integer cantidad = producto.getCantidad();
			Integer categoria_id = producto.getCategoriaId();
		
		// convierte a variables de su tipo cada llamada de la tabla de base de datos//
		
		final PreparedStatement statement = conexion.prepareStatement("INSERT INTO PRODUCTOS(NOMBRE, DESCRIPCION"
				+ "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS ); 
		
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
		
		final ResultSet resultset = statement.getGeneratedKeys();
		try(resultset){
			
			while(resultset.next()) {
				System.out.println(String.format("Fue registrado un %d", producto.toString()));
		}
		}
	}

	public List<Producto> listar() throws SQLException {
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

	public static List<Categoria> listar(Categoria categoria) throws SQLException {
		
		List<Categoria> resultado = new ArrayList<>();

        try {
       
            final PreparedStatement statement = conexion.prepareStatement(
            		"SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD "
            	            + " FROM PRODUCTO WHERE CATEGORIA_ID = ?");
    
            try (statement) {
                statement.setInt(1, categoria.getId());
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
	
    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
        try {
            final PreparedStatement statement = conexion.prepareStatement(
                    "UPDATE PRODUCTO SET "
                    + " NOMBRE = ?, "
                    + " DESCRIPCION = ?,"
                    + " CANTIDAD = ?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = conexion.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	}
	



