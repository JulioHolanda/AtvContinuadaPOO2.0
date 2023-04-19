package br.gov.cesarschool.poo.fidelidade.cliente.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;

public class ClienteDAO {
	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String DIR_BASE = "." + FILE_SEP + "banco" + FILE_SEP + "clienteFidelidade" + FILE_SEP;
	private static final String EXT = ".dat";

	public ClienteDAO() {
		File diretorio = new File(DIR_BASE);
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
	}

	public boolean incluir(Cliente cliente) {
		String arq = getArquivo(cliente.getCpf());
		if (new File(arq).exists()) {
			return false; 
		}
		incluirAux(cliente, arq);
		return true;
	}

	public boolean alterar(Cliente cliente) {
		String arq = getArquivo(cliente.getCpf());
		if (!new File(arq).exists()) {
			return false; 
		}
		if (!new File(arq).delete()) {
			return false;
		}
		incluirAux(cliente, arq);
		return true;
	}

	public Cliente buscar(String cpf) {
		try {
			String arquivo = getArquivo(cpf);
			File file = new File(arquivo);
			if (file.exists()) {
				return buscarAux(file);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Cliente buscarAux(File file) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			Cliente cliente = (Cliente) ois.readObject();
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void incluirAux(Cliente cliente, String arq) {
		try (FileOutputStream fos = new FileOutputStream(arq); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(cliente);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao incluir conta: " + e.getMessage());
		}
	}

	private String getArquivo(String cpf) {
		return DIR_BASE + cpf + EXT;
	}
}
