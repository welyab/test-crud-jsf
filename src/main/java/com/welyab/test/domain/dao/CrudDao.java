package com.welyab.test.domain.dao;

public interface CrudDao<Tipo, Pk> extends AutoCloseable{

	public Tipo buscarPorId(Pk pk);

	public void salvar(Tipo tipo);

	public void atualizar(Tipo tipo);

	public void remover(Pk pk);
}
