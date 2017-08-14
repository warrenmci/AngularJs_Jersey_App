package com.salon.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
	
	public Connection getDBConnection() {
		Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("No PostgreSQL JDBC Driver, try include in project library path");
			cnfe.printStackTrace();
			return null;
		}

		try {
			System.out.println("PostgreSQL JDBC Driver Registered!");
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/salondb", "warren","warren");
		} catch (SQLException sqle) {
			System.out.println("Connection Failed! Check output console");
			sqle.printStackTrace();
			return null;
		}

		return connection;
	}
	
}