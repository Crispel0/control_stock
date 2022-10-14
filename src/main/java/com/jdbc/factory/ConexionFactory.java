package com.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionFactory { //clase para evitar la duplicidad de codigo al momento de crear conexiones con base datos
	
	public Connection RecuperaConexion() throws SQLException {
	return DriverManager.getConnection("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
                "root",
                "Cr¡st¡@n_901");
	}

}
