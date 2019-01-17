package com.welyab.test.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import com.welyab.test.domain.ConnectionManager;
import com.welyab.test.domain.dao.EmpresaDao;
import com.welyab.test.domain.model.Empresa;

@Stateless
public class EmpresaService {

	public void salvar(Empresa empresa) {
		if (empresa.getNome() == null) {
			throw new RuntimeException("Não foi possível salvar a empresa porque ele não tem nome");
		}

		try (EmpresaDao empresaDao = new EmpresaDao(ConnectionManager.getConnection())) {
			empresaDao.inserir(empresa);
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível salvar a empresa porque aconteceu um erro de banco de dados",
				e);
		}
	}

	public List<Empresa> consulta() {
		try (EmpresaDao empresaDao = new EmpresaDao(ConnectionManager.getConnection())) {
			return empresaDao.consultar();
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível consultar a lista de empresas",
				e);
		}
	}
}
