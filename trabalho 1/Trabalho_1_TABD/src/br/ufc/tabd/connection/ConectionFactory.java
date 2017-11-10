package br.ufc.tabd.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionFactory {

	
	public Connection getConnection () {
		try {
			return DriverManager.getConnection("jdbc:postgresql:"
		    + "//localhost:5432/Trabalho_1", "postgres", "postgres");
		} catch (SQLException e) {
		  throw new RuntimeException(e);	
		}
	}
	
}
