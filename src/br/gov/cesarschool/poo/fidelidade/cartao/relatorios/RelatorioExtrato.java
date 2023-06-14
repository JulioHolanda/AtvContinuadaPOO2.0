package br.gov.cesarschool.poo.fidelidade.cartao.relatorios;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.RetornoConsultaExtrato;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.excecoes.ExcecaoDadoInvalido;
import br.gov.cesarschool.poo.fidelidade.util.Ordenador;

public class RelatorioExtrato {
	private static final Scanner ENTRADA = new Scanner(System.in);
	
	public static void gerarRelatorioExtratos(Long numeroCartaoLong, LocalDateTime inicio, LocalDateTime fim) throws ExcecaoDadoInvalido {
		String numeroCartao = numeroCartaoLong+"";
		CartaoFidelidadeMediator mediator = CartaoFidelidadeMediator.getInstance();
		
		//RetornoConsultaExtrato retorno = mediator.consultaEntreDatas(numeroCartao, inicio, fim);
		//if(retorno != null && retorno.getLancamentos() != null) {
			//LancamentoExtrato[] lancamentos = retorno.getLancamentos();
			LancamentoExtrato[] lancamentos = mediator.consultaEntreDatas(numeroCartao, inicio, fim);
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
		//}
	}
	public static void main(String[] args) {
		
		long numeroCartao = ENTRADA.nextLong();
		
		String dataInicio = ENTRADA.nextLine();
		LocalDateTime dateTimeInicio = LocalDateTime.parse(dataInicio, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		
		String dataFim = ENTRADA.nextLine();
		LocalDateTime dateTimeFim = LocalDateTime.parse(dataFim, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		
		System.out.println(dateTimeInicio+"");
		System.out.println(dateTimeFim+"");
		
		try {
			gerarRelatorioExtratos(numeroCartao, dateTimeInicio, dateTimeFim);
		}catch (ExcecaoDadoInvalido e) {
			System.out.println(e.getMessage());
		}
	}
}
