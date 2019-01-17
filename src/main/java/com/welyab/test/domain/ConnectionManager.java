package com.welyab.test.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:myDb", "sa", null);

			String sql = ""
				+ "  create table if not exists empresa ( "
				+ "      id integer identity primary key,"
				+ "      nome varchar(100),"
				+ "      atuacao varchar(200),"
				+ "      faturamento numeric(10,2)      "
				+ "  )";

			connection.prepareStatement(sql).execute();

			return connection;
		} catch (SQLException e) {
			// ignorar
			e.printStackTrace();
			return null;
		}
	}
}
