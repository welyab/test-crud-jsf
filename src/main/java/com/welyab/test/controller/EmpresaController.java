package com.welyab.test.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.welyab.test.domain.model.Empresa;
import com.welyab.test.service.EmpresaService;

@Named
@RequestScoped
public class EmpresaController {

	private Empresa empresa;

	@Inject
	private EmpresaService empresaService;

	public void salvar() {
		empresaService.salvar(empresa);
	}

	public EmpresaController() {
		empresa = new Empresa();
	}

	public Empresa getEmpresa() {
		return empresa;
	}
}
