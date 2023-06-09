package br.gov.cesarschool.poo.fidelidade.cliente.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.dao.DAOGenerico;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;

public class ClienteDAO {
	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String DIR_BASE = "." + FILE_SEP + "fidelidade" + FILE_SEP + "cliente" + FILE_SEP;

	private DAOGenerico<Cliente> daoEncapsulado;

	public ClienteDAO() {
		daoEncapsulado = new DAOGenerico<>(DIR_BASE);
	}

	public boolean incluir(Cliente cliente) {
		return daoEncapsulado.incluir(cliente);
	}

	public boolean alterar(Cliente cliente) {
		return daoEncapsulado.alterar(cliente);
	}

	public Cliente buscar(String cpf) {
		return daoEncapsulado.buscar(cpf);
	}
	
	public Cliente[] buscarTodos() {
		Cliente[] clientes = daoEncapsulado.buscarTodos();
		return clientes;
		
	}
	
}