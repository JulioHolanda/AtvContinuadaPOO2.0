package br.gov.cesarschool.poo.fidelidade.cliente.negocio;

import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.cliente.dao.ClienteDAO;

import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.util.ValidadorCPF;
import br.gov.cesarschool.poo.fidelidade.util.StringUtil;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;

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
        String validacao = validar(cliente);
        if (validacao == null) {
            repositorioCliente.incluir(cliente);
            long numeroCartaoFidelidade = cartaoMediator.gerarCartao(cliente);
            return new ResultadoInclusaoCliente(numeroCartaoFidelidade, null);
        } else {
            return new ResultadoInclusaoCliente(0, validacao);
        }
    }

    public String alterar(Cliente cliente) {
        String validacao = validar(cliente);
        if (validacao == null) {
            repositorioCliente.alterar(cliente);
            return null;
        } else {
            return validar(cliente);
        }
    }

    	
	private String validar(Cliente cliente) {
	    if (ValidadorCPF.ehCpfValido(cliente.getCpf()) == false) {
	        return "CPF Invalido";
	    }

	    if (StringUtil.ehNuloOuBranco(cliente.getNomeCompleto())) {
	        return "Nome Invalido";
	    }

	    if (cliente.getSexo() == null) {
	        return "Sexo Invalido";
	    }

	    if (cliente.obterIdade() < 18) {
	        return "Cliente com idade menor a 18 anos";
	    }

	    if (cliente.getRenda() < 0) {
	        return "Renda Invalida";
	    }

	    Endereco end = cliente.getEndereco();
	    if (end == null) {
	        return "Endereço Invalido";
	    }

	    if (StringUtil.ehNuloOuBranco(end.getLogradouro()) || end.getLogradouro().length() < 4) {
	        return "Endereço Logradouro Invalido"; 
	    }

	    if (end.getNumero() < 0) {
	        return "Endereço Numero Invalido";
	    }

	    if (StringUtil.ehNuloOuBranco(end.getCidade())) {
	        return "Endereço Cidade Invalido"; 
	    }
	    
	    if (StringUtil.ehNuloOuBranco(end.getEstado())) {
	        return "Endereço Estado Invalido"; 
	    }

	    if (StringUtil.ehNuloOuBranco(end.getPais())) {
	        return "Endereço Pais Invalido"; 
	    }

	    return null;

    }
}