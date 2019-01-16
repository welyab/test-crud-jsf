package com.welyab.test.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;

import com.google.common.base.Preconditions;

import com.welyab.test.domain.conn.ConnectionManager;
import com.welyab.test.domain.dao.FuncionarioDao;
import com.welyab.test.domain.model.Funcionario;

@Stateless
public class FuncionarioService {

	public void salvarOuEditar(Funcionario funcionario) {
		try (FuncionarioDao funcionarioDao = new FuncionarioDao(ConnectionManager.getConnection())) {
			if (funcionario.getId() == null) {
				funcionario.setId(funcionarioDao.getProximoIdFuncionario());
				funcionarioDao.salvar(funcionario);
			} else {
				funcionarioDao.atualizar(funcionario);
			}
		}
	}

	public Funcionario buscarPorId(Long idFuncionario) {
		Preconditions.checkNotNull("idFuncionario", "Parâmetro 'idFuncionario' não pode ser nulo");
		try (FuncionarioDao funcionarioDao = new FuncionarioDao(ConnectionManager.getConnection())) {
			return funcionarioDao.buscarPorId(idFuncionario);
		}
	}

	public List<Funcionario> buscarPorNomeData_Inicio_Trabalho(String nome,LocalDate  data_Inicio_Trabalho) {
		try (FuncionarioDao funcionarioDao = new FuncionarioDao(ConnectionManager.getConnection())) {
			return funcionarioDao.buscarPorNomeData_Inicio_Trabalho(nome, data_Inicio_Trabalho);
		}
	}
}
