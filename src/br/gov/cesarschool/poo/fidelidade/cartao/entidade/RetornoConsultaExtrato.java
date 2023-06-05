package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

public class RetornoConsultaExtrato {
    private LancamentoExtrato[] lancamentos;
    private String mensagemErro;
    
	public RetornoConsultaExtrato(LancamentoExtrato[] lancamentos, String mensagemErro) {
		this.lancamentos = lancamentos;
		this.mensagemErro = mensagemErro;
	}
	
	public LancamentoExtrato[] getLancamentos() {
		return lancamentos;
	}
	
	public void setLancamentos(LancamentoExtrato[] lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}
	
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
    
}
