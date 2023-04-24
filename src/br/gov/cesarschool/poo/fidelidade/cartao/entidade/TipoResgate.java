package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.io.Serializable;

public enum TipoResgate implements Serializable {
    PRODUTO(1, "produto"),
    SERVICO(2, "serviço"),
    VIAGEM(3, "viagem");

    private final int codigo;
    private final String descricao;

    TipoResgate(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}