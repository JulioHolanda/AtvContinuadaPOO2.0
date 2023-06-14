package br.gov.cesarschool.poo.fidelidade.geral.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;
 //<T extends Identificavel>
public class DAOGenerico <T extends Identificavel>{
	private static final String EXT = ".dat";
	public String diretorioBase;

	public DAOGenerico(String diretorioBase) {
		this.diretorioBase = diretorioBase;
		File diretorio = new File(this.diretorioBase);
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}

	}

	private File getArquivo(String chave) {
		String nomeArq = diretorioBase + chave + EXT;
		return new File(nomeArq);
	}
	
	private File getArquivo(T ident) {
		 return getArquivo(ident.obterChave());
	}

	private void incluirAux(T ident, File arq) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(arq);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ident);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao incluir arquivo" + e.getMessage());
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
			}
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}

	public boolean incluir(T ident) {
		File arq = getArquivo(ident);
		if (arq.exists()) {
			return false;
		}
		incluirAux(ident, arq);
		return true;
	}

	public boolean alterar(T ident) {
		File arq = getArquivo(ident);
		if (!arq.exists()) {
			return false;
		}
		if (!arq.delete()) {
			return false;
		}
		incluirAux(ident, arq);
		return true;
	}

	@SuppressWarnings("unchecked")
	public T buscar(String chave) {
		File arq = getArquivo(chave);
		if (!arq.exists()) {
			return null;
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(arq);
			ois = new ObjectInputStream(fis);
			return (T) ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler chave");
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
			}
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
	}
	
	public T[] buscarTodos() {
		T[] arrayVazio = null;
		
		File diretorio = new File(diretorioBase);
		
		if (!diretorio.exists()) {
			return null;
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			
			File[] files = diretorio.listFiles((dir, name) -> name.toLowerCase().endsWith(".dat"));
			
			if(files.length == 0) {
				return arrayVazio;
			}
			
			Identificavel[] ident = new Identificavel[files.length];
			
			int cont = 0;
			
			for(File file: files) {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				
				ident[cont] = (T) ois.readObject();
						
				cont += 1;
			}
			T[] identRet = (T[]) ident;
			return  identRet;

		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler chave" + e.getMessage());
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
			}
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
	}

}
