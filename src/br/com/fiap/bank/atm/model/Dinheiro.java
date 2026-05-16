package br.com.fiap.bank.atm.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class Dinheiro {
    private final BigDecimal valor;

    public Dinheiro(BigDecimal valor) {
        if (valor == null) throw new IllegalArgumentException("Valor não pode ser nulo");
        this.valor = valor.setScale(2, java.math.RoundingMode.HALF_UP);
    }

    public BigDecimal getValor() { return valor; }

    public Dinheiro somar(Dinheiro outro) {
        return new Dinheiro(this.valor.add(outro.valor));
    }

    public Dinheiro subtrair(Dinheiro outro) {
        return new Dinheiro(this.valor.subtract(outro.valor));
    }

    public Boolean menorQue(Dinheiro outro) {
        return this.valor.compareTo(outro.valor) < 0;
    }

    public Boolean menorOuIgualAZero() {
        return this.valor.compareTo(BigDecimal.ZERO) <= 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dinheiro dinheiro = (Dinheiro) obj;
        return valor.compareTo(dinheiro.valor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return "R$ " + valor;
    }
}