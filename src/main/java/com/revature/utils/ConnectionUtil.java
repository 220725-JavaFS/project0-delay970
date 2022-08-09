package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	// singleton means that only one object can exist at a time
	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			return connection;
		} else {
			try { // letting jbdc know what driver we are using
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			String url = "jdbc:postgresql://javafs220725.cex4kjijdkn4.us-east-2.rds.amazonaws.com:5432/project_0";
			String username = System.getenv("username"); // can hide with ENV varibles using System.getenv("name");
			String password = System.getenv("password");

			connection = DriverManager.getConnection(url, username, password);

			return connection;
		}

	}
//
//	public static void main(String[] args) {
//		try {
//			getConnection();
//			System.out.println("works");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
