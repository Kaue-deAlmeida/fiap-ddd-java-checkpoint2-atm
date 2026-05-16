package br.com.fiap.bank.atm.application;

import br.com.fiap.bank.atm.model.Conta;

public class AutorizacaoService {
    private final Conta conta;

    public AutorizacaoService(Conta conta) {
        this.conta = conta;
    }

    public Boolean autorizar(String senha) {
        if (conta.getContaAcesso().isBloqueado()) {
            return Boolean.FALSE;
        }
        return conta.getContaAcesso().validarSenha(senha);
    }
}