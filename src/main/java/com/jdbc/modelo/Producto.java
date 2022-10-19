package com.jdbc.modelo;
/*
 * Representar un producto un registo "modelo"
 */
public class Producto {
	private int id;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private Integer categoriaId;
	
	public Producto(int id, String nombre, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
	
	}
	
	public Producto(String Nombre, String Descripcion, Integer cantidad) {
		this.nombre = Nombre;
		this.descripcion = Descripcion;
		this.cantidad = cantidad;
	}

	public Producto(int id, String nombre, String descripcion, int cantidad) {
		
	this.id = id;
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.cantidad = cantidad;
	
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setCategoriaId(Integer categoriaId){
		this.categoriaId = categoriaId;
	}
	 
	//Metodo toString sirve para mostar por pantalla en la view los valores de cada atributo y no mostrar las referencias del objeto//
	@Override
		public String toString() {
			return String.format("{id: %s, nombre: %s, descripcion: %s, cantidad: %d)",  //String tipo Json llave : valor//
					this.id,
					this.nombre,
					this.descripcion,
					this.cantidad);
		}

	public int getCategoriaId() {
		return this.categoriaId;
	}

	
}
