package br.com.fiap.bank.atm.model;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {
    private static final Dinheiro TAXA_SAQUE = new Dinheiro(new BigDecimal("2.50"));

    public ContaCorrente(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        super(cliente, contaAcesso, saldo, 12.00);
    }

    @Override
    protected void aplicarRegraDeTaxa() {
        if (this.saldo.menorQue(TAXA_SAQUE)) {
            throw new IllegalStateException("Saldo insuficiente para cobrir a taxa de saque.");
        }
        this.saldo = this.saldo.subtrair(TAXA_SAQUE);
        registrarMovimentacao(TipoMovimentacao.TAXA, TAXA_SAQUE);
    }
}