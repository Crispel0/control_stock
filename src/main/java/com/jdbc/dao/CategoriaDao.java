package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jdbc.modelo.Categoria;
import com.jdbc.modelo.Producto;


public class CategoriaDao {
	
	private Connection con;
	
	public CategoriaDao(Connection con) {
		this.con = con;
	}

	public List<Categoria> listar() {
		
		List<Categoria> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE FROM CATEGORIA");

				try(statement){
			//automaticamente ejecuta el query y no le tenemos que guardar y despues obtener su resultado por medio del ResultSet//
					final ResultSet resultset = statement.executeQuery(); 
					try(resultset){
						
				}
					while(resultset.next()) {
						
						var categoria = new Categoria(resultset.getInt("ID"),
						resultset.getString("NOMBRE"));
						
						resultado.add(categoria);
					}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;	

	}

	public List<Categoria> listarConProductos() {
		
		List<Categoria> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT C.ID, C.NOMBRE FROM CATEGORIA C"
					+ "INNER JOIN PRODUCTOS P ON C.ID = P.CATEGORIA_ID");

				try(statement){
			
					/*automaticamente ejecuta el query y no le tenemos que guardar y 
					 despues obtener su resultado por medio del ResultSet*/
					
					final ResultSet resultset = statement.executeQuery(); 
					try(resultset){
						
				}
					while(resultset.next()) {
						
						//Crea una nueva categoria de no existir y si el id de categoria es igual a categoriaID sigue iterando//
						
						Integer categoriaId = resultset.getInt("C.ID");
						String string = resultset.getString("C.NOMBRE");
						
						var categoria = resultado.stream().
										filter(cat -> cat.getId().equals(categoriaId)).
										findAny().orElseGet(() -> {
											
											 Categoria cat = new Categoria(categoriaId, string);
												
												resultado.add(cat);
												
												return cat;
										});
						
						Producto producto = new Producto(resultset.getInt("P.ID"),
												resultset.getString("P.NOMBRE"),
												resultset.getInt("P.CANTIDAD"));
						
						categoria.agregar(producto);
					}
				};
			}catch(SQLException e) {
			throw new RuntimeException(e);
			}
		
		return resultado;	
	}	

}
