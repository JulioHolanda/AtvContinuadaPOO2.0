package br.gov.cesarschool.poo.fidelidade.cartao.negocio;

import java.time.LocalDateTime;

import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.LancamentoExtratoDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.TipoResgate;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;

public class CartaoFidelidadeMediator {
	private static CartaoFidelidadeDAO repositorioCartao;
    private static LancamentoExtratoDAO repositorioLancamento;
    private static CartaoFidelidadeMediator instancia;

    private CartaoFidelidadeMediator(){
        repositorioCartao = new CartaoFidelidadeDAO();
        repositorioLancamento = new LancamentoExtratoDAO();
    }

    public static CartaoFidelidadeMediator getInstance() {
        if (instancia == null) {
            instancia = new CartaoFidelidadeMediator();
        }
        return instancia;
    }

    public long gerarCartao(Cliente cliente){
        long numeroCartaoFidelidade;
        LocalDateTime data = LocalDateTime.now(); 
        numeroCartaoFidelidade = Long.parseLong(cliente.getCpf()) + data.getYear() + data.getMonthValue() + data.getDayOfMonth();
        CartaoFidelidade cartaoFidelidade= new CartaoFidelidade(numeroCartaoFidelidade);
        repositorioCartao.incluir(cartaoFidelidade);
        if(repositorioCartao.buscar(numeroCartaoFidelidade) != null){
            return numeroCartaoFidelidade;
        }else{
            return 0;
        }

    }

    public static String pontuar(long numeroCartao, double quantidadePontos){
        LocalDateTime data = LocalDateTime.now();
        if(quantidadePontos <= 0){
            return "A quantidade de pontos inserida é invalida";
        }
        CartaoFidelidade cartaoFidelidade = repositorioCartao.buscar(numeroCartao);
        if(cartaoFidelidade == null){
            return "O número do cartão não foi encontrando no repositório";
        }
        cartaoFidelidade.creditar(quantidadePontos);
        repositorioCartao.alterar(cartaoFidelidade);
        LancamentoExtratoPontuacao lancamentoExtrato = new LancamentoExtratoPontuacao(numeroCartao, (int) quantidadePontos, data);
        repositorioLancamento.incluir(lancamentoExtrato);
        return null;
    }

    public static String resgatar(long numeroCartao, double quantidadePontos, TipoResgate tipo){
        LocalDateTime data = LocalDateTime.now();
        if(quantidadePontos <= 0){
            return "A quantidade de pontos inserida é invalida";
        }

        CartaoFidelidade cartaoFidelidade = repositorioCartao.buscar(numeroCartao);

        if(cartaoFidelidade == null){
            return "O número do cartão não foi encontrando no repositório";
        }

        if(cartaoFidelidade.getSaldo() < quantidadePontos){
            return "Valor de resgate menor que seu saldo";
        }

        cartaoFidelidade.debitar(quantidadePontos);

        repositorioCartao.alterar(cartaoFidelidade);

        LancamentoExtratoResgate lancamentoExtrato = new LancamentoExtratoResgate(numeroCartao, (int) quantidadePontos, data, tipo);
        repositorioLancamento.incluir(lancamentoExtrato);
        return null;

    }


}