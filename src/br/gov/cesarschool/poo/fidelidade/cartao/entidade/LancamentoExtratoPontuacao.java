package br.gov.cesarschool.poo.fidelidade.cartao.entidade;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LancamentoExtratoPontuacao extends LancamentoExtrato {

	public LancamentoExtratoPontuacao(long numeroCartao, double quantidadePontos, LocalDateTime dataHoraLancamento) {
		super(numeroCartao, quantidadePontos, dataHoraLancamento);
	}

	@Override
	public String obterChave() {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String chaveLancamento = "P" + this.getNumeroCartao()+ "_" + timestamp; 
		return chaveLancamento;
	}
	
}
