package br.gov.cesarschool.poo.fidelidade.cartao.relatorios;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Month;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.RetornoConsultaExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.util.Ordenador;

public class RelatorioExtrato {
	private static final Scanner ENTRADA = new Scanner(System.in);
	
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
	public static void main(String[] args) {
		long numeroCartao = ENTRADA.nextLong();
		int diaInicio = ENTRADA.nextInt();
		int mesInicio = ENTRADA.nextInt();
		int anoInicio = ENTRADA.nextInt();
		int horaInicio = ENTRADA.nextInt();
		int diaFim = ENTRADA.nextInt();
		int mesFim = ENTRADA.nextInt();
		int anoFim = ENTRADA.nextInt();
		int horaFim = ENTRADA.nextInt();
		
		Month mesInicioFormatado = Month.of(mesInicio);
		Month mesFimFormatado = Month.of(mesFim);
		
		LocalDateTime dataTimeInicio = LocalDateTime.of(anoInicio, mesInicioFormatado, diaInicio, horaInicio, 0);
		LocalDateTime dataTimeFim = LocalDateTime.of(anoFim, mesFimFormatado, diaFim, horaFim, 0);
		
		gerarRelatorioExtratos(numeroCartao+"", dataTimeInicio, dataTimeFim);
		
	}
}
