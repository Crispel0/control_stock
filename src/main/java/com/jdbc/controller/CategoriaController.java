package com.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.CategoriaDao;
import com.jdbc.factory.ConexionFactory;
import com.jdbc.modelo.Categoria;

public class CategoriaController {
	
	private CategoriaDao categoriaDao;
	
	public CategoriaController() {
		var factory = new ConexionFactory();
		this.categoriaDao = new CategoriaDao(factory.RecuperaConexion());
	}

	public List<Categoria> listar() {
		return CategoriaDao.listar();
	}
	
	//Retorna una lista de Categoria //
    public List<Categoria> cargaReporte() {
        return this.categoriaDao.listarConProductos();
    }

}
