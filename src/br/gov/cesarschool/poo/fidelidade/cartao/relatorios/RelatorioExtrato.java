package br.gov.cesarschool.poo.fidelidade.cartao.relatorios;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.RetornoConsultaExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.util.Ordenador;

public class RelatorioExtrato {
	public static void gerarRelatorioExtratos(String numeroCartao, LocalDateTime inicio, LocalDateTime fim) {
		CartaoFidelidadeMediator mediator = CartaoFidelidadeMediator.getInstance();
		RetornoConsultaExtrato retorno = mediator.consultaEntreDatas(numeroCartao, inicio, fim);
		if(retorno != null && retorno.getLancamentos() != null) {
			LancamentoExtrato[] lancamentos = retorno.getLancamentos();
			Ordenador ordenator = new Ordenador();
			ordenator.ordenar(lancamentos);
			for(LancamentoExtrato lancamento:lancamentos) {
				String dataLancamento = "";
				if(lancamento.getDataHoraLancamento() != null) {
					SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
					dataLancamento = formatador.format(lancamento.getDataHoraLancamento());
				}
		        
				System.out.println("Data de Lançamento: " + dataLancamento + "\nValor: " + lancamento.getquantidadePontos() + "\nTipo: " + lancamento.getIdentificadorTipo());
			}
		}
	}
}
