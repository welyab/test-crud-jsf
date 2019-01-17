package com.welyab.test.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.welyab.test.domain.model.Empresa;

public class EmpresaDao implements AutoCloseable {

	private Connection connection;

	public EmpresaDao(Connection connection) {
		this.connection = connection;

	}

	public void inserir(Empresa e) throws SQLException {
		String sql = "insert into empresa(nome, atuacao, faturamento) values (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, e.getNome());
		statement.setString(2, e.getAtuacao());
		statement.setBigDecimal(3, e.getFaturamento());
		statement.execute();
	}

	public List<Empresa> consultar() throws SQLException {
		String sql = "select id, nome, atuacao, faturamento from empresa";
		ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
		List<Empresa> empresas = new ArrayList<>();
		while (resultSet.next()) {
			Empresa empresa = montarEmpresa(resultSet);
			empresas.add(empresa);
		}
		return empresas;
	}

	private Empresa montarEmpresa(ResultSet resultSet) throws SQLException {
		Empresa empresa = new Empresa();
		empresa.setId(resultSet.getLong("id"));
		empresa.setNome(resultSet.getString("nome"));
		empresa.setAtuacao(resultSet.getString("atuacao"));
		empresa.setFaturamento(resultSet.getBigDecimal("faturamento"));
		return empresa;
	}

	@Override
	public void close() throws SQLException {
		connection.close();
	}

}
