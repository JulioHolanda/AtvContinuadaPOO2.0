package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.time.LocalDateTime;

import br.gov.cesarschool.poo.fidelidade.geral.entidade.Comparavel;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Identificavel;

public abstract class LancamentoExtrato extends Identificavel implements Comparavel{
	private long numeroCartao;
	private double quantidadePontos;
	private LocalDateTime dataHoraLancamento = LocalDateTime.now();
	
	
	public LancamentoExtrato(long numeroCartao, double quantidadePontos, LocalDateTime dataHoraLancamento) {
		super();
		this.numeroCartao = numeroCartao;
		this.quantidadePontos = quantidadePontos;
		this.dataHoraLancamento = dataHoraLancamento;
	}
		
	public long getNumeroCartao() {
		return numeroCartao;
	}
	
	public double getquantidadePontos() {
		return quantidadePontos;
	}
	
	public LocalDateTime getDataHoraLancamento() {
		return dataHoraLancamento;
	}
	
	@Override
	public int comparar(Comparavel comparavel) {
		LancamentoExtrato aComparar = (LancamentoExtrato)comparavel;
		if(aComparar.getDataHoraLancamento().isBefore(this.getDataHoraLancamento())) {
			return 1;
		}else if (aComparar.getDataHoraLancamento().isAfter(this.getDataHoraLancamento())) {
			return -1;
		}
		return 0;
		
	}
	public abstract String getIdentificadorTipo();

}
