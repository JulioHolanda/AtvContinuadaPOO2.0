package br.gov.cesarschool.poo.fidelidade.cliente.negocio;
import java.util.Date;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Sexo;
import java.util.Calendar;
import br.gov.cesarschool.poo.fidelidade.cliente.dao.ClienteDAO;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.dao.DAOGenerico;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Sexo;
import br.gov.cesarschool.poo.fidelidade.util.*;

import javax.print.attribute.standard.DateTimeAtCompleted;

import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;

public class ClienteMediator {
	
	private static ClienteMediator instance;
	public ClienteDAO repositorioCliente;
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
            	msgErro = "Erro ao incluir cliente no reposit�rio";
            }
        } 
        return new ResultadoInclusaoCliente(numeroCartao, msgErro);
    }

    public String alterar(Cliente cliente) {
    	String msgErro = validar(cliente); 
        if (msgErro == null){
            boolean res = repositorioCliente.alterar(cliente);
            if (!res) {
            	msgErro = "Erro ao alterar cliente no reposit�rio";
            }
        }
        return msgErro;
    }
	
	private String validar(Cliente cliente) {
	    if(ValidadorCPF.ehCpfValido(cliente.getCpf()) == false){
	        return "CPF Inv�lido";
	    }

	    else if (StringUtil.ehNuloOuBranco(cliente.getNomeCompleto())){
	        return "Nome Inv�lido";
	    }

	    else if (cliente.getSexo() == null) {
	        return "Sexo Inv�lido";
	    }

	    else if (cliente.obterIdade() < 18) {
	        return "A idade deve ser maior ou igual a 18.";
	    }

	    else if (cliente.getRenda() < 0) {
	        return "Renda Inv�lida";
	    }

	    else if (cliente.getEndereco() == null) {
	        return "Endere�o Inv�lido";
	    }

	    else if (StringUtil.ehNuloOuBranco(cliente.getEndereco().getLogradouro()) || cliente.getEndereco().getLogradouro().length() < 4) {
	        return "Logradouro Inv�lido"; 
	    }

	    else if (cliente.getEndereco().getNumero() < 0) {
	        return "Numero de endere�o inv�lido";
	    }

	    else if (StringUtil.ehNuloOuBranco(cliente.getEndereco().getCidade())){
	        return "Cidade Inv�lida"; 
	    }
	    
	    else if (StringUtil.ehNuloOuBranco(cliente.getEndereco().getEstado())) {
	        return "Estado Inv�lida"; 
	    }

	    else if (StringUtil.ehNuloOuBranco(cliente.getEndereco().getPais())) {
	        return "Pais Inv�lido"; 
	    }
	    return null;
    }

	public Cliente buscarCliente(String cpf) {
	    Cliente cliente = repositorioCliente.buscar(cpf);
	    return cliente;
	}
	
	//@SuppressWarnings("static-access")
	public Cliente[] consultarClientesOrdenadosPorNome() {
		Cliente[] clientes = repositorioCliente.buscarTodos();
		
		Ordenador ordenator = new Ordenador();
		ordenator.ordenar(clientes);
		return clientes;
		
	}
	/*
	public static void main(String[] args) {
		
		String diretorioBase = "Walter-chan/";
		
		ClienteDAO dao = new ClienteDAO();
		
		Cliente WalterPai = new Cliente("10084039469");
		
		Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());

        calendar.add(Calendar.DAY_OF_YEAR, -(365*50));

        Date ontem = calendar.getTime();
		WalterPai.setDataNascimento(ontem);
		WalterPai.setRenda(1000);
		WalterPai.setSexo(Sexo.MASCULINO);
		WalterPai.setEndereco();

		WalterPai.setNomeCompleto("Roberto Barret");
		
		ClienteMediator cMediator = ClienteMediator.getInstance();
		ResultadoInclusaoCliente res = cMediator.incluir(WalterPai);
		System.out.println(res.getMensagemErroValidacao() );
		
	}
	 * */
	
}
