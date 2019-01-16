package com.welyab.test.domain.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

import com.welyab.test.domain.model.Funcionario;

public class FuncionarioDao extends JdbcDao<Funcionario, Long> implements CrudDao<Funcionario, Long> {

	public FuncionarioDao(Connection connection) {
		super(connection);
	}

	@Override
	public Funcionario buscarPorId(Long pk) {
		Preconditions.checkNotNull(pk, "Parâmetro pk não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("select id,id_empresa, nome, email, data_inicio_trabalho from funcionario where id = ?")) {
			statement.setLong(1, pk);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			return montarFuncionario(resultSet);
		} catch (SQLException e) {
			throw new DaoException(String.format("Falha ao consultar Funcionario com id = %d", pk), e);
		}
	}

	@Override
	public void salvar(Funcionario tipo) {
		Preconditions.checkNotNull(tipo, "Parâmetro pk não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("insert into funcionario (id,id_empresa, nome, email, data_inicio_trabalho) values(?, ?, ?, ?)")) {
			statement.setLong(1, tipo.getId());
			statement.setLong(2, tipo.getIdEmpresa());
			statement.setString(3, tipo.getNome());
			statement.setString(4, tipo.getEmail());
			//statement.LocalDate(5, tipo.getDataInicioTrabalho());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(
				String.format("Falha na tentativa de salvar o funcionario com nome = %s", tipo.getNome()), e
			);
		}
	}

	@Override
	public void atualizar(Funcionario tipo) {
		Preconditions.checkNotNull(tipo, "Parâmetro pk não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("update funcionario set IdEmpresa = ?, nome = ?, email = ?, data_inicio_trabalho = ? where id = ?")) {
			statement.setLong(1, tipo.getIdEmpresa());
			statement.setString(2, tipo.getNome());
			statement.setString(3, tipo.getEmail());
			//statement.LocalDate(5, tipo.getDataInicioTrabalho());
			statement.setLong(4, tipo.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(
				String.format("Falha na tentativa de atualizar funcionario com ID = %d", tipo.getId()), e
			);
		}
	}

	@Override
	public void remover(Long pk) {
		Preconditions.checkNotNull(pk, "Parâmetro pk não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("delete from funcionario e where e.id = ?")) {
			statement.setLong(1, pk);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(String.format("Falha ao consultar Funcionario com id = %d", pk), e);
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

	private Funcionario montarFuncionario(ResultSet rs) throws SQLException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(rs.getLong("id"));
		funcionario.setIdEmpresa(rs.getLong("idEmpresa"));
		funcionario.setNome(rs.getString("nome"));
		funcionario.setEmail(rs.getString("email"));
		//funcionario.DataInicioTrabalho(rs.getLocalDate("dataInicioTrabalho"));
		return funcionario;
	}

	public Long getProximoIdFuncionario() {
		try (PreparedStatement statement = getConnection()
			.prepareStatement("select apl_teste.funcionario_id_seq.nextval id from dual")) {
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getLong(1);
		} catch (SQLException e) {
			throw new DaoException("Falha ao criar ID para Funcionario", e);
		}
	}

	public List<Funcionario> buscarPorNomeData_Inicio_Trabalho(String nome,LocalDate  data_Inicio_Trabalho) {
		Preconditions.checkNotNull(nome, "Parâmetro 'nome' não pode ser nulo");
		Preconditions.checkNotNull(data_Inicio_Trabalho, "Parâmetro 'data' não pode ser nulo");
		try (PreparedStatement statement = getConnection()
			.prepareStatement("select id, nome, email, dataInicioTrabalho from funcionario where nome like ? and email like ? and dataInicioTrabalho like ?")) {
			statement.setString(1, "%" + nome + "%");
			ResultSet resultSet = statement.executeQuery();
			List<Funcionario> funcionarios = new ArrayList<>();
			while (resultSet.next()) {
				funcionarios.add(montarFuncionario(resultSet));
			}
			return funcionarios;
		} catch (SQLException e) {
			throw new DaoException("Falha ao consultar funcionario", e);
		}
	}
}
