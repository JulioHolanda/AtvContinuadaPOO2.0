package br.gov.cesarschool.poo.fidelidade.cliente.relatorios;

import java.util.Date;
import java.text.SimpleDateFormat;

import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.cliente.negocio.ClienteMediator;

public class RelatorioCliente {
	
	public static void gerarRelatorioClientes() {
		ClienteMediator mediator = ClienteMediator.getInstance();
		Cliente[] clientes = mediator.consultarClientesOrdenadosPorNome();
		for(Cliente cliente:clientes) {
			String dataNascimento = "";
			if(cliente.getDataNascimento() != null) {
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				dataNascimento = formatador.format(cliente.getDataNascimento());
			}
	        
			System.out.println("Nome : " + cliente.getNomeCompleto() + "\nData de Nascimento: " + dataNascimento + "\nRenda: " + cliente.getRenda());
		}
	}
	
	public static void main(String[] args) {
		
		gerarRelatorioClientes();	
		
	}

}
