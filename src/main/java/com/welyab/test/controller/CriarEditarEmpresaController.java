package com.welyab.test.controller;

import java.io.IOException;

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
public class CriarEditarEmpresaController {

	private Empresa empresa;

	@Inject
	private EmpresaService empresaService;

	public CriarEditarEmpresaController() {
		empresa = new Empresa();
	}

	public void inicializar() throws IOException {
		String idEmpresParam = Faces.getRequestParameter("idEmpresa");
		if (StringUtils.isNotBlank(idEmpresParam)) {
			empresa = empresaService.buscarPorId(Long.parseLong(idEmpresParam));
			if (empresa == null) {
				Faces.getExternalContext().responseSendError(404, "Empresa não encontrada");
				return;
			}
		}
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void salvar() {
		try {
			boolean editar = isEditar();
			empresaService.salvarOuEditar(empresa);
			if (editar) {
				Faces.getContext()
					.addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa", "Empresa atualizada com sucesso")
					);
			} else {
				Faces.getContext()
					.addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa", "Empresa salva com sucesso")
					);
			}
		} catch (Exception e) {
			Faces.getContext()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Empresa", "Falha ao atualizar empresa"));
		}
	}

	public boolean isEditar() {
		return empresa != null && empresa.getId() != null && empresa.getId() > 0;
	}
}
