package com.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	
	private Integer id;
	private String nombre;
	
	//Ref de producto en la Categoria//
	private List<Producto> productos;
	
	public Categoria (Integer id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	//Agrega nuevos productos en el caso de que no exista y despues lo agrega a el parametro de producto//
	public void agregar(Producto producto) {
		if(this.productos == null) {
			this.productos = new ArrayList<>();
		}
		
		this.productos.add(producto);
	}
	
	public List<Producto> getProductos() {
		return this.productos;
	}


}
