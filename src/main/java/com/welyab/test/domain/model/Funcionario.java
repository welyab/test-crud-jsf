package com.welyab.test.domain.model;

import java.time.LocalDate;

public class Funcionario {

	private Long id;

	private Long idEmpresa;

	private String nome;

	private String email;

	private LocalDate dataInicioTrabalho;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataInicioTrabalho() {
		return dataInicioTrabalho;
	}

	public void setDataInicioTrabalho(LocalDate dataInicioTrabalho) {
		this.dataInicioTrabalho = dataInicioTrabalho;
	}
}
