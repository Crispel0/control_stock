package com.jdbc.factory;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConexionFactory { //clase para evitar la duplicidad de codigo al momento de crear conexiones con base datos
	
	private DataSource datasource;
	
	//metodo para manejar la cantidad de pooles"conexiones" a nuestra base datos//
	public ConexionFactory() {

	var pooledDataSource = new ComboPooledDataSource();
	pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");
	pooledDataSource.setUser("root");
	pooledDataSource.setPassword("Cr¡st¡@n_901");
	pooledDataSource.setMaxPoolSize(10); //Configuracion maximo de pool conexiones//
		
				
	this.datasource = pooledDataSource;
	}
	
	
		
	public Connection RecuperaConexion()  { //Controla la exception de raiz//
	try {
		return this.datasource.getConnection();
	}catch(SQLException e) {
		throw new RuntimeException(e);
	}
	}
	
	
		
	
	
	};
	


