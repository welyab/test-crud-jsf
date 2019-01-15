package com.welyab.test.domain.conn;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

	private static DataSource dataSource;

	private ConnectionManager() {
	}

	private static DataSource getDataSource() throws IOException {
		if (dataSource != null) {
			return dataSource;
		}
		Properties properties = new Properties();
		try (FileInputStream f = new FileInputStream(System.getProperty("user.home") + "/teste.config.properties")) {
			properties.load(f);
		}

		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(properties.getProperty("teste.jdbc.url"));
		ds.setUsername(properties.getProperty("teste.jdbc.user"));
		ds.setPassword(properties.getProperty("teste.jdbc.pass"));
		return dataSource = ds;
	}

	public static Connection getConnection() {
		try {
			return getDataSource().getConnection();
		} catch (SQLException | IOException e) {
			throw new ConnectionException("Falha ao obter conexão com o banco de dados", e);
		}
	}
}
