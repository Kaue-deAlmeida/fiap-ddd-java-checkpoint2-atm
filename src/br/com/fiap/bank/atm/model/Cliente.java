package br.com.fiap.bank.atm.model;

public class Cliente extends BaseEntity {
    private final String nomeCompleto;

    public Cliente(String nomeCompleto) {
        super();
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome completo é obrigatório");
        }
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeCompleto() { return nomeCompleto; }

    public String obterPrimeiroNome() {
        return nomeCompleto.split(" ")[0];
    }
}