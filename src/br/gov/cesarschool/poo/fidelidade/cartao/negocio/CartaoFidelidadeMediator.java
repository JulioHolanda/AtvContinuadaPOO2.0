package br.gov.cesarschool.poo.fidelidade.cartao.negocio;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.LancamentoExtratoDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.TipoResgate;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.util.ValidadorCPF;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;

public class CartaoFidelidadeMediator {

	private static final String ERRO_AO_INCLUIR_LANCAMENTO = "Erro ao incluir lançamento";
	private static final String ERRO_NA_ALTERACAO_DO_CARTAO = "Erro na alteraçãoo do cartão";
	private static final String CARTAO_INEXISTENTE = "Cartão não existe";
	private static final String QUANTIDADE_DE_PONTOS_MENOR_QUE_ZERO = "Quantidade de pontos menor que zero";
	private static CartaoFidelidadeMediator instance;
	private CartaoFidelidadeDAO repositorioCartao;
	private LancamentoExtratoDAO repositorioLancamento;
	
	
	private CartaoFidelidadeMediator() {
		repositorioCartao = new CartaoFidelidadeDAO();
		repositorioLancamento = new LancamentoExtratoDAO();
	}
	
	public static CartaoFidelidadeMediator getInstance() {
		if (instance == null) {	
			instance = new CartaoFidelidadeMediator();
		}
		return instance;
	}
	
	public long gerarCartao(Cliente cliente) {
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");		
		String cpf = cliente.getCpf();		
		if (ValidadorCPF.ehCpfValido(cpf) == false) {
			return 0;
		}		
		String cpfSemDigitosVerificadores = cpf.substring(0, cpf.length() - 2);		
		String cartao = cpfSemDigitosVerificadores + date.format(new Date()); 
		long cardZinho = Long.parseLong(cartao);		
		CartaoFidelidade cardinhu = new CartaoFidelidade(cardZinho);		
		repositorioCartao.incluir(cardinhu);				
		return cardZinho;
	}

	private String processarAlteracaoCartaoInclusaoLancamento(CartaoFidelidade cardinhu, 
			int qtdPontos, TipoResgate tipo) {
		boolean res = repositorioCartao.alterar(cardinhu);
		if (!res) {
			return ERRO_NA_ALTERACAO_DO_CARTAO;
		}
		LocalDateTime now = LocalDateTime.now();
		if (tipo == null) {
			res = repositorioLancamento.incluir(new LancamentoExtratoPontuacao(
					cardinhu.getNumeroFidelidade(), qtdPontos, now));
		} else {
			res = repositorioLancamento.incluir(new LancamentoExtratoResgate(
					cardinhu.getNumeroFidelidade(), qtdPontos, now, tipo));			
		}		
		if (!res) {
			return ERRO_AO_INCLUIR_LANCAMENTO;
		}
		return null;
	}
	
	public String pontuar(long numeroCartao, int qtdPontos) {
		if (qtdPontos <= 0 ) {
			return QUANTIDADE_DE_PONTOS_MENOR_QUE_ZERO;
		}		
		CartaoFidelidade cardinhu = repositorioCartao.buscar((numeroCartao+""));		
		if(cardinhu == null) {
			return CARTAO_INEXISTENTE;
		}		
		cardinhu.creditar(qtdPontos);
		return processarAlteracaoCartaoInclusaoLancamento(cardinhu, 
				qtdPontos, null);
	}
	
	public String resgatar(long numeroCartao, int qtdPontos, TipoResgate tipo) {		
		if (qtdPontos <= 0) {
			return QUANTIDADE_DE_PONTOS_MENOR_QUE_ZERO;
		}		
	    CartaoFidelidade cardinhu = repositorioCartao.buscar((numeroCartao+""));
	    if (cardinhu == null) {
	        return CARTAO_INEXISTENTE;
	    }
	    if (cardinhu.getSaldo() < qtdPontos) {
	        return "Saldo insuficiente para realizar o resgate.";
	    }	        
        cardinhu.debitar(qtdPontos);
		return processarAlteracaoCartaoInclusaoLancamento(cardinhu, 
				qtdPontos, null);
	}
	
	public CartaoFidelidade buscarCartao(long numeroCartao) {
	    CartaoFidelidade cartao = repositorioCartao.buscar((numeroCartao+""));
	    return cartao;
	}
}
