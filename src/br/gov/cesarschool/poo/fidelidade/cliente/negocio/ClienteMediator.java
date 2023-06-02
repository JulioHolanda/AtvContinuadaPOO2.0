package br.gov.cesarschool.poo.fidelidade.cliente.negocio;

import br.gov.cesarschool.poo.fidelidade.cliente.dao.ClienteDAO;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.util.*;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;

public class ClienteMediator {
	
	private static ClienteMediator instance;
	private ClienteDAO repositorioCliente;
	private CartaoFidelidadeMediator cartaoMediator;
	
	private ClienteMediator() {
		repositorioCliente = new ClienteDAO();
		cartaoMediator = CartaoFidelidadeMediator.getInstance();
	}
	
	public static ClienteMediator getInstance() {
        if (instance == null) {
            instance = new ClienteMediator(); 
        }
        return instance;
    }
	public ResultadoInclusaoCliente incluir(Cliente cliente) {
		String msgErro = validar(cliente); 
		long numeroCartao = 0;
        if (msgErro == null){
            boolean res = repositorioCliente.incluir(cliente);
            if (res) {
            	numeroCartao = cartaoMediator.gerarCartao(cliente);
            } else {
            	msgErro = "Erro ao incluir cliente no repositório";
            }
        } 
        return new ResultadoInclusaoCliente(numeroCartao, msgErro);
    }

    public String alterar(Cliente cliente) {
    	String msgErro = validar(cliente); 
        if (msgErro == null){
            boolean res = repositorioCliente.alterar(cliente);
            if (!res) {
            	msgErro = "Erro ao alterar cliente no repositório";
            }
        }
        return msgErro;
    }
	
	private String validar(Cliente cliente) {
	    if(ValidadorCPF.ehCpfValido(cliente.getCpf()) == false){
	        return "CPF Inválido";
	    }

	    else if (StringUtil.ehNuloOuBranco(cliente.getNomeCompleto())){
	        return "Nome Inválido";
	    }

	    else if (cliente.getSexo() == null) {
	        return "Sexo Inválido";
	    }

	    else if (cliente.obterIdade() < 18) {
	        return "A idade deve ser maior ou igual a 18.";
	    }

	    else if (cliente.getRenda() < 0) {
	        return "Renda Inválida";
	    }

	    else if (cliente.getEndereco() == null) {
	        return "Endereço Inválido";
	    }

	    else if (StringUtil.ehNuloOuBranco(cliente.getEndereco().getLogradouro()) || cliente.getEndereco().getLogradouro().length() < 4) {
	        return "Logradouro Inválido"; 
	    }

	    else if (cliente.getEndereco().getNumero() < 0) {
	        return "Numero de endereço inválido";
	    }

	    else if (StringUtil.ehNuloOuBranco(cliente.getEndereco().getCidade())){
	        return "Cidade Inválida"; 
	    }
	    
	    else if (StringUtil.ehNuloOuBranco(cliente.getEndereco().getEstado())) {
	        return "Estado Inválida"; 
	    }

	    else if (StringUtil.ehNuloOuBranco(cliente.getEndereco().getPais())) {
	        return "Pais Inválido"; 
	    }
	    return null;
    }

	public Cliente buscarCliente(String cpf) {
	    Cliente cliente = repositorioCliente.buscar(cpf);
	    return cliente;
	}
	
}
