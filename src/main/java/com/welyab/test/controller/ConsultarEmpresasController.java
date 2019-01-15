package com.welyab.test.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;

import com.welyab.test.domain.model.Empresa;
import com.welyab.test.service.EmpresaService;

@Named
@RequestScoped
public class ConsultarEmpresasController {

	@Inject
	private EmpresaService empresaService;

	private Empresa empresa;

	private List<Empresa> empresas;

	public ConsultarEmpresasController() {
		empresa = new Empresa();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void consultar() {
		boolean erroValidacao = false;
		if (StringUtils.isBlank(empresa.getNome())) {
			Faces.getContext()
				.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Consulta de empresa", "Informe o nome da empresa")
				);
			erroValidacao = true;
		}
		if (empresa.getFaturamento() == null) {
			Faces.getContext()
				.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Consulta de empresa", "Informe o valor do faturamento")
				);
			erroValidacao = true;
		}
		if (erroValidacao) {
			return;
		}
		empresas = empresaService
			.buscarPorNomeFaturamento(empresa.getNome(), empresa.getFaturamento());
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}
}
