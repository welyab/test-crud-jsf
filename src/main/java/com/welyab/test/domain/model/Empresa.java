package com.welyab.test.domain.model;

import java.math.BigDecimal;

public class Empresa {

	private Long id;

	private String nome;

	private String atuacao;

	private BigDecimal faturamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAtuacao() {
		return atuacao;
	}

	public void setAtuacao(String atuação) {
		atuacao = atuação;
	}

	public BigDecimal getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(BigDecimal faturamento) {
		this.faturamento = faturamento;
	}

}
