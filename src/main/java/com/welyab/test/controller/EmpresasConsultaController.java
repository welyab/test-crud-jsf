package com.welyab.test.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.welyab.test.domain.model.Empresa;
import com.welyab.test.service.EmpresaService;

@Named
@RequestScoped
public class EmpresasConsultaController {

	@Inject
	private EmpresaService empresaService;

	private List<Empresa> empresas;

	public void consultar() {
		empresas = empresaService.consulta();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}
}
