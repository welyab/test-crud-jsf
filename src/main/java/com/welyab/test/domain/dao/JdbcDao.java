package com.welyab.test.domain.dao;

import java.sql.Connection;

public abstract class JdbcDao<Tipo, Pk> implements CrudDao<Tipo, Pk> {

	private final Connection connection;

	public JdbcDao(Connection connection) {
		super();
		this.connection = connection;
	}

	protected Connection getConnection() {
		return connection;
	}
}
