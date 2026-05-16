package br.com.fiap.bank.atm.model;

public class ContaPoupanca extends Conta {
    private static final Double RENDIMENTO_MENSAL = 0.005; // 0.5%

    public ContaPoupanca(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        super(cliente, contaAcesso, saldo, RENDIMENTO_MENSAL);
    }

    @Override
    protected void aplicarRegraDeTaxa() {
        // Poupança não cobra taxa no saque
    }

    public void renderJuros() {
        java.math.BigDecimal valorRendimento = this.saldo.getValor().multiply(java.math.BigDecimal.valueOf(RENDIMENTO_MENSAL));
        Dinheiro rendimento = new Dinheiro(valorRendimento);
        this.saldo = this.saldo.somar(rendimento);
        registrarMovimentacao(TipoMovimentacao.RENDIMENTO, rendimento);
    }
}