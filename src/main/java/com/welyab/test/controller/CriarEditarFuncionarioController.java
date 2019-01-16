package com.welyab.test.controller;

import java.io.IOException;

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
public class CriarEditarFuncionarioController {

	private Funcionario funcionario;

	@Inject
	private FuncionarioService funcionarioService;

	public CriarEditarFuncionarioController() {
		funcionario = new Funcionario();
	}

	public void inicializar() throws IOException {
		String idParam = Faces.getRequestParameter("id");
		if (StringUtils.isNotBlank(idParam)) {
			funcionario = funcionarioService.buscarPorId(Long.parseLong(idParam));
			if (funcionario == null) {
				Faces.getExternalContext().responseSendError(404, "Funcionario não encontrado");
				return;
			}
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void salvar() {
		try {
			boolean editar = isEditar();
			funcionarioService.salvarOuEditar(funcionario);
			if (editar) {
				Faces.getContext()
					.addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionario", "Funcionario atualizado com sucesso")
					);
			} else {
				Faces.getContext()
					.addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionario", "Funcionario salvo com sucesso")
					);
			}
		} catch (Exception e) {
			Faces.getContext()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Funcionario", "Falha ao atualizar funcionario"));
		}
	}

	public boolean isEditar() {
		return funcionario != null && funcionario.getId() != null && funcionario.getId() > 0;
	}
}
