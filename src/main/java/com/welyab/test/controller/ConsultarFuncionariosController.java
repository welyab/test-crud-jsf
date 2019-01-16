package com.welyab.test.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;

import com.welyab.test.domain.model.Funcionario;
import com.welyab.test.service.FuncionarioService;

@Named
@RequestScoped
public class ConsultarFuncionariosController {

	@Inject
	private FuncionarioService funcionarioService;

	private Funcionario funcionario;

	private List<Funcionario> funcionarios;

	public ConsultarFuncionariosController() {
		funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void consultar() {
		boolean erroValidacao = false;
		if (StringUtils.isBlank(funcionario.getNome())) {
			Faces.getContext()
				.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Consulta de funcionario", "Informe o nome da funcionario")
				);
			erroValidacao = true;
		}
		if (funcionario.getDataInicioTrabalho() == null) {
			Faces.getContext()
				.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Consulta de funcionario", "Informe a data de admissão")
				);
			erroValidacao = true;
		}
		if (erroValidacao) {
			return;
		}
		funcionarios = funcionarioService
			.buscarPorNomeData_Inicio_Trabalho(funcionario.getNome(), funcionario.getDataInicioTrabalho());
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
}
