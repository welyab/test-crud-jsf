package com.welyab.test.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import com.google.common.base.Preconditions;

import com.welyab.test.domain.conn.ConnectionManager;
import com.welyab.test.domain.dao.EmpresaDao;
import com.welyab.test.domain.model.Empresa;

@Stateless
public class EmpresaService {

	public void salvarOuEditar(Empresa empresa) {
		try (EmpresaDao empresaDao = new EmpresaDao(ConnectionManager.getConnection())) {
			if (empresa.getId() == null) {
				empresa.setId(empresaDao.getProximoIdEmpresa());
				empresaDao.salvar(empresa);
			} else {
				empresaDao.atualizar(empresa);
			}
		}
	}

	public Empresa buscarPorId(Long idEmpresa) {
		Preconditions.checkNotNull("idEmpresa", "Parâmetro 'idEmpresa' não pode ser nulo");
		try (EmpresaDao empresaDao = new EmpresaDao(ConnectionManager.getConnection())) {
			return empresaDao.buscarPorId(idEmpresa);
		}
	}

	public List<Empresa> buscarPorNomeFaturamento(String nome, BigDecimal faturamento) {
		try (EmpresaDao empresaDao = new EmpresaDao(ConnectionManager.getConnection())) {
			return empresaDao.buscarPorNomeFaturamento(nome, faturamento);
		}
	}
}
