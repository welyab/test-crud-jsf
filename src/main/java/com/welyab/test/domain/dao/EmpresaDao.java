package com.welyab.test.domain.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

import com.welyab.test.domain.model.Empresa;

public class EmpresaDao extends JdbcDao<Empresa, Long> implements CrudDao<Empresa, Long> {

	public EmpresaDao(Connection connection) {
		super(connection);
	}

	@Override
	public Empresa buscarPorId(Long pk) {
		Preconditions.checkNotNull(pk, "Parâmetro pk não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("select id, nome, atuacao, faturamento from empresa where id = ?")) {
			statement.setLong(1, pk);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			return montarEmpresa(resultSet);
		} catch (SQLException e) {
			throw new DaoException(String.format("Falha ao consultar Empresa com id = %d", pk), e);
		}
	}

	@Override
	public void salvar(Empresa tipo) {
		Preconditions.checkNotNull(tipo, "Parâmetro pk não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("insert into empresa(id, nome, atuacao, faturamento) values(?, ?, ?, ?)")) {
			statement.setLong(1, tipo.getId());
			statement.setString(2, tipo.getNome());
			statement.setString(3, tipo.getAtuacao());
			statement.setBigDecimal(4, tipo.getFaturamento());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(
				String.format("Falha na tentativa de salvar a empresa com nome = %s", tipo.getNome()), e
			);
		}
	}

	@Override
	public void atualizar(Empresa tipo) {
		Preconditions.checkNotNull(tipo, "Parâmetro pk não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("update empresa set nome = ?, atuacao = ?, faturamento = ? where id = ?")) {
			statement.setString(1, tipo.getNome());
			statement.setString(2, tipo.getAtuacao());
			statement.setBigDecimal(3, tipo.getFaturamento());
			statement.setLong(4, tipo.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(
				String.format("Falha na tentativa de atualizar empresa com ID = %d", tipo.getId()), e
			);
		}
	}

	@Override
	public void remover(Long pk) {
		Preconditions.checkNotNull(pk, "Parâmetro pk não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("delete from empresa e where e.id = ?")) {
			statement.setLong(1, pk);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(String.format("Falha ao consultar Empresa com id = %d", pk), e);
		}
	}

	@Override
	public void close() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			throw new DaoException("Falha ao fechar conexão associada com este DAO", e);
		}
	}

	private Empresa montarEmpresa(ResultSet rs) throws SQLException {
		Empresa empresa = new Empresa();
		empresa.setId(rs.getLong("id"));
		empresa.setNome(rs.getString("nome"));
		empresa.setAtuacao(rs.getString("atuacao"));
		empresa.setFaturamento(rs.getBigDecimal("faturamento"));
		return empresa;
	}

	public Long getProximoIdEmpresa() {
		try (PreparedStatement statement = getConnection()
			.prepareStatement("select apl_teste.empresa_id_seq.nextval id from dual")) {
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getLong(1);
		} catch (SQLException e) {
			throw new DaoException("Falha ao criar ID para Empresa", e);
		}
	}

	public List<Empresa> buscarPorNomeFaturamento(String nome, BigDecimal faturamento) {
		Preconditions.checkNotNull(nome, "Parâmetro 'nome' não pode ser nulo");
		Preconditions.checkNotNull(faturamento, "Parâmetro 'faturamento' não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("select id, nome, atuacao, faturamento from empresa where nome like ? and faturamento like ?")) {
			statement.setString(1, "%" + nome + "%");
			statement.setString(2, "%" + faturamento + "%");
			ResultSet resultSet = statement.executeQuery();
			List<Empresa> empresas = new ArrayList<>();
			while (resultSet.next()) {
				empresas.add(montarEmpresa(resultSet));
			}
			return empresas;
		} catch (SQLException e) {
			throw new DaoException("Falha ao consultar empresas", e);
		}
	}
}
