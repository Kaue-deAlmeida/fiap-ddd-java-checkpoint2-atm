package br.com.fiap.bank.atm.model;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Movimentacao {
    private final LocalDateTime dataHora;
    private final TipoMovimentacao tipo;
    private final Dinheiro valor;

    public Movimentacao(TipoMovimentacao tipo, Dinheiro valor) {
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
    }

    public LocalDateTime getDataHora() { return dataHora; }
    public TipoMovimentacao getTipo() { return tipo; }
    public Dinheiro getValor() { return valor; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movimentacao that = (Movimentacao) obj;
        return Objects.equals(dataHora, that.dataHora) && tipo == that.tipo && Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataHora, tipo, valor);
    }
}