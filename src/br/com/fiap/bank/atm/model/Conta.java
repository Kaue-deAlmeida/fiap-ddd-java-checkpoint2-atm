package br.com.fiap.bank.atm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta extends BaseEntity {
    protected Cliente cliente;
    protected Dinheiro saldo;
    protected Double taxa;
    protected StatusConta status;
    protected LocalDate dataAbertura;
    protected ContaAcesso contaAcesso;
    protected List<Movimentacao> movimentacoes;

    public Conta(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo, Double taxa) {
        super();
        this.cliente = cliente;
        this.contaAcesso = contaAcesso;
        this.saldo = saldo;
        this.taxa = taxa;
        this.status = StatusConta.ATIVA;
        this.dataAbertura = LocalDate.now();
        this.movimentacoes = new ArrayList<>();
    }

    public final void realizarSaque(Dinheiro valor) {
        if (valor.menorOuIgualAZero()) throw new IllegalArgumentException("Valor de saque inválido.");
        if (this.saldo.menorQue(valor)) throw new IllegalStateException("Saldo insuficiente.");

        sacar(valor);
        registrarMovimentacao(TipoMovimentacao.SAQUE, valor);
        aplicarRegraDeTaxa();
    }

    public final void realizarDeposito(Dinheiro valor) {
        if (valor.menorOuIgualAZero()) throw new IllegalArgumentException("Valor de depósito inválido.");
        depositar(valor);
        registrarMovimentacao(TipoMovimentacao.DEPOSITIO, valor);
    }

    private void sacar(Dinheiro valor) { this.saldo = this.saldo.subtrair(valor); }
    private void depositar(Dinheiro valor) { this.saldo = this.saldo.somar(valor); }

    protected abstract void aplicarRegraDeTaxa();

    protected void registrarMovimentacao(TipoMovimentacao tipo, Dinheiro valor) {
        this.movimentacoes.add(new Movimentacao(tipo, valor));
    }

    public Dinheiro getSaldo() { return saldo; }
    public Cliente getCliente() { return cliente; }
    public ContaAcesso getContaAcesso() { return contaAcesso; }
    public List<Movimentacao> getMovimentacoes() { return Collections.unmodifiableList(movimentacoes); }
    public StatusConta getStatus() { return status; }
    public LocalDate getDataAbertura() { return dataAbertura; }
}