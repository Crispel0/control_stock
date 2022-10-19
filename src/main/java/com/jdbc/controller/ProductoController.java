package com.jdbc.controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.factory.ConexionFactory;
import com.jdbc.modelo.Categoria;
import com.jdbc.modelo.Producto;

public class ProductoController {
	
	public ProductoController(){
		
		ProductoDao productoDao = new ProductoDao(new ConexionFactory().RecuperaConexion());
	}
	public int modificar(String nombre, String descripcion,Integer cantidad, Integer id) throws SQLException {
		
		Connection conexion = new ConexionFactory().RecuperaConexion();
		
		try(conexion){
			
			final PreparedStatement statement = conexion.prepareStatement("UPTADE FROM PRODUCTOS SET"
					+ "NOMBRE = ?"
					+ ", DESCRIPCION = ?"
					+ ", CANTIDAD = ?"
					+ " ID = ?");
			
			try(statement){
				
				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, cantidad);
				statement.setInt(4, id);
				
				statement.execute();
				
				int updateacount = statement.getUpdateCount();
			
				return updateacount;
		}
	}
}

	public int eliminar(Integer id) throws SQLException {
		
		Connection conexion = new ConexionFactory().RecuperaConexion();
		//El try con recursos es para cerrar las conexiones
		try(conexion){
			PreparedStatement statement = conexion.prepareStatement("DELETE FROM PRODUCTOS WHERE = ?");
			try(statement){
			statement.setInt(1, id);
		
			return statement.getUpdateCount(); //metodo para conocer cuantos registros son eliminados despues de una query returna un int
			
			}
		}
	}

	public List<Producto> listar() throws SQLException{
		
			return ProductoDao.listar();
		
	}

	 //Metodo crea una conexion y la asigna y lo guarda//
    public void guardar(Producto producto, Integer categoria) throws SQLException  {
    	
    	ProductoDao productoDao = new ProductoDao(new ConexionFactory().RecuperaConexion());
    	
    	//Asigna una categoria a el producto y despues se guarda en el productoDao//
    	
    	producto.setCategoriaId(categoria);
    	productoDao.guardarProducto(producto);
    }	
}
